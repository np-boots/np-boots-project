package cn.np.boots.plugin.maven.pkg.container.tool;

import jdk.internal.org.objectweb.asm.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MainClassFinder {
    private static final String DOT_CLASS = ".class";
    private static final String MAIN_METHOD_NAME = "main";
    private static final Type STRING_ARRAY_TYPE = Type.getType(String[].class);
    private static final Type MAIN_METHOD_TYPE = Type.getMethodType(Type.VOID_TYPE, STRING_ARRAY_TYPE);


    public static String findSingleMainClass(JarFile jarFile, String classesLocation, String annotationName) throws IOException {
        SingleMainClassCallback callback = new SingleMainClassCallback(annotationName);
        MainClassFinder.doWithMainClasses(jarFile, classesLocation, callback);
        return callback.getMainClassName();
    }

    static <T> T doWithMainClasses(JarFile jarFile, String classesLocation,
                                   MainClassCallback<T> callback) throws IOException {
        List<JarEntry> classEntries = getClassEntries(jarFile, classesLocation);
        Collections.sort(classEntries, new ClassEntryComparator());
        for (JarEntry entry : classEntries) {
            InputStream inputStream = new BufferedInputStream(jarFile.getInputStream(entry));
            try {
                ClassDescriptor classDescriptor = createClassDescriptor(inputStream);
                if (classDescriptor != null && classDescriptor.isMainMethodFound()) {
                    String className = convertToClassName(entry.getName(), classesLocation);
                    T result = callback.doWith(new MainClass(className, classDescriptor
                            .getAnnotationNames()));
                    if (result != null) {
                        return result;
                    }
                }
            } finally {
                inputStream.close();
            }
        }
        return null;
    }

    private static List<JarEntry> getClassEntries(JarFile source, String classesLocation) {
        classesLocation = (classesLocation != null ? classesLocation : "");
        Enumeration<JarEntry> sourceEntries = source.entries();
        List<JarEntry> classEntries = new ArrayList<>();
        while (sourceEntries.hasMoreElements()) {
            JarEntry entry = sourceEntries.nextElement();
            if (entry.getName().startsWith(classesLocation) && entry.getName().endsWith(DOT_CLASS)) {
                classEntries.add(entry);
            }
        }
        return classEntries;
    }

    private static ClassDescriptor createClassDescriptor(InputStream inputStream) {
        try {
            ClassReader classReader = new ClassReader(inputStream);
            ClassDescriptor classDescriptor = new ClassDescriptor();
            classReader.accept(classDescriptor, ClassReader.SKIP_CODE);
            return classDescriptor;
        } catch (IOException ex) {
            return null;
        }
    }

    private static String convertToClassName(String name, String prefix) {
        name = name.replace('/', '.');
        name = name.replace('\\', '.');
        name = name.substring(0, name.length() - DOT_CLASS.length());
        if (prefix != null) {
            name = name.substring(prefix.length());
        }
        return name;
    }


    interface MainClassCallback<T> {
        T doWith(MainClass mainClass);
    }

    static final class MainClass {
        private final String name;
        private final Set<String> annotationNames;

        MainClass(String name, Set<String> annotationNames) {
            this.name = name;
            this.annotationNames = Collections.unmodifiableSet(new HashSet<>(annotationNames));
        }

        String getName() {
            return this.name;
        }
        Set<String> getAnnotationNames() {
            return this.annotationNames;
        }
        @Override
        public String toString() {
            return this.name;
        }
        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            MainClass other = (MainClass) obj;
            return this.name.equals(other.name);
        }
    }

    private static final class SingleMainClassCallback implements MainClassCallback<Object> {

        private final Set<MainClass> mainClasses = new LinkedHashSet<>();

        private final String annotationName;

        private SingleMainClassCallback(String annotationName) {
            this.annotationName = annotationName;
        }

        @Override
        public Object doWith(MainClass mainClass) {
            this.mainClasses.add(mainClass);
            return null;
        }

        private String getMainClassName() {
            Set<MainClass> matchingMainClasses = new LinkedHashSet<>();
            if (this.annotationName != null) {
                for (MainClass mainClass : this.mainClasses) {
                    if (mainClass.getAnnotationNames().contains(this.annotationName)) {
                        matchingMainClasses.add(mainClass);
                    }
                }
            }
            if (matchingMainClasses.isEmpty()) {
                matchingMainClasses.addAll(this.mainClasses);
            }
            if (matchingMainClasses.size() > 1) {
                throw new IllegalStateException(
                        "Unable to find a single main class from the following candidates "
                                + matchingMainClasses);
            }
            return matchingMainClasses.isEmpty() ? null : matchingMainClasses.iterator().next()
                    .getName();
        }

    }

    private static class ClassEntryComparator implements Comparator<JarEntry> {

        @Override
        public int compare(JarEntry o1, JarEntry o2) {
            Integer d1 = getDepth(o1);
            Integer d2 = getDepth(o2);
            int depthCompare = d1.compareTo(d2);
            if (depthCompare != 0) {
                return depthCompare;
            }
            return o1.getName().compareTo(o2.getName());
        }

        private int getDepth(JarEntry entry) {
            return entry.getName().split("/").length;
        }

    }

    private static class ClassDescriptor extends ClassVisitor {

        private final Set<String> annotationNames = new LinkedHashSet<>();

        private boolean           mainMethodFound;

        ClassDescriptor() {
            super(Opcodes.ASM4);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            this.annotationNames.add(Type.getType(desc).getClassName());
            return null;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                         String[] exceptions) {
            if (isAccess(access, Opcodes.ACC_PUBLIC, Opcodes.ACC_STATIC)
                    && MAIN_METHOD_NAME.equals(name) && MAIN_METHOD_TYPE.getDescriptor().equals(desc)) {
                this.mainMethodFound = true;
            }
            return null;
        }

        private boolean isAccess(int access, int... requiredOpsCodes) {
            for (int requiredOpsCode : requiredOpsCodes) {
                if ((access & requiredOpsCode) == 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isMainMethodFound() {
            return this.mainMethodFound;
        }

        Set<String> getAnnotationNames() {
            return this.annotationNames;
        }

    }
}
