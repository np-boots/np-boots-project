package cn.np.boots.plugin.maven.pkg.container.pkg;

import cn.np.boots.plugin.maven.pkg.container.common.ArkUtils;
import cn.np.boots.plugin.maven.pkg.container.common.Constants;
import cn.np.boots.plugin.maven.pkg.container.common.ZipUtils;
import cn.np.boots.plugin.maven.pkg.container.jar.JarWriter;
import cn.np.boots.plugin.maven.pkg.container.libraries.Libraries;
import cn.np.boots.plugin.maven.pkg.container.libraries.Library;
import cn.np.boots.plugin.maven.pkg.container.libraries.LibraryScope;
import cn.np.boots.plugin.maven.pkg.container.tool.MainClassFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class NpContainerPackager {
    private static final String ARK_VERSION_ATTRIBUTE = "Sofa-Ark-Version";
    private static final String SPRING_BOOT_APPLICATION_CLASS_NAME = "org.springframework.boot.autoconfigure.SpringBootApplication";
    private static final long FIND_WARNING_TIMEOUT = TimeUnit.SECONDS.toMillis(10);
    private final File source;
    private File executableFatJar;
    private File pluginModuleJar;
    private boolean packageProvided;
    private boolean skipArkExecutable;
    private Library arkContainerLibrary = null;
    private String mainClass;
    private final List<Library> arkModuleLibraries = new ArrayList<>();
    private final List<Library> arkPluginLibraries = new ArrayList<>();
    private final List<Library> standardLibraries = new ArrayList<>();

    private List<MainClassTimeoutWarningListener> mainClassTimeoutListeners          = new ArrayList<>();

    public void doPackage(File appDestination, File moduleDestination, Libraries libraries) throws IOException {
        if (appDestination == null || appDestination.isDirectory() || moduleDestination == null
                || moduleDestination.isDirectory()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        if (libraries == null) {
            throw new IllegalArgumentException("Libraries must not be null");
        }

        if (alreadyRepackaged()) {
            return;
        }

        executableFatJar = appDestination;
        pluginModuleJar = moduleDestination;

        libraries.doWithLibraries(library -> {
            if (LibraryScope.PROVIDED.equals(library.getScope()) && !packageProvided) {
                return;
            }
            if (!ZipUtils.isZip(library.getFile())) {
                return;
            }

            try (JarFile jarFile = new JarFile(library.getFile())) {
                if (ArkUtils.isArkContainer(jarFile)) {
                    if (arkContainerLibrary != null) {
                        throw new RuntimeException("duplicate SOFAArk Container dependency");
                    }
                    library.setScope(LibraryScope.CONTAINER);
                    arkContainerLibrary = library;
                } else if (ArkUtils.isArkModule(jarFile)) {
                    library.setScope(LibraryScope.MODULE);
                    arkModuleLibraries.add(library);
                } else if (ArkUtils.isArkPlugin(jarFile)) {
                    library.setScope(LibraryScope.PLUGIN);
                    arkPluginLibraries.add(library);
                } else {
                    standardLibraries.add(library);
                }
            }
        });

        repackageModule();
    }

    private void repackageModule() throws IOException {
        if (this.skipArkExecutable) return;
        File destination = executableFatJar.getAbsoluteFile();
        destination.delete();

        JarFile jarFileSource = new JarFile(arkContainerLibrary.getFile().getAbsoluteFile());
        JarWriter writer = new JarWriter(destination);

        Manifest manifest = buildModuleManifest(jarFileSource);

        try{
            writer
        }
    }

    public Manifest buildModuleManifest(JarFile source) throws IOException {
        Manifest manifest = source.getManifest();
        if (manifest == null) {
            manifest = new Manifest();
            manifest.getMainAttributes().put("Manifest-Version", "1.0");
        }
        manifest = new Manifest();

        String startClass = this.mainClass;
        if (startClass == null) {
            startClass = manifest.getMainAttributes().getValue(Constants.MAIN_CLASS_ATTRIBUTE);
        }
        if (startClass == null) {
            startClass = findMainMethodWithTimeoutWarning(source);
        }
        if (startClass == null) {
            throw new IllegalStateException("Unable to find main class.");
        }

        manifest.getMainAttributes().putValue(Constants.MAIN_CLASS_ATTRIBUTE, startClass);
    }

    public String findMainMethodWithTimeoutWarning(JarFile source) throws IOException {
        long startTime = System.currentTimeMillis();
        String mainMethod = MainClassFinder.findSingleMainClass(source, null, SPRING_BOOT_APPLICATION_CLASS_NAME);
        long duration = System.currentTimeMillis() - startTime;
        if (duration > FIND_WARNING_TIMEOUT) {
            for (MainClassTimeoutWarningListener listener : this.mainClassTimeoutListeners) {
                listener.handleTimeoutWarning(duration, mainMethod);
            }
        }
        return mainMethod;
    }

    public interface MainClassTimeoutWarningListener {
        void handleTimeoutWarning(long duration, String mainMethod);
    }

    private boolean alreadyRepackaged() throws IOException {
        try (JarFile jarFile = new JarFile(this.source)) {
            Manifest manifest = jarFile.getManifest();
            return (manifest != null && manifest.getMainAttributes().getValue(ARK_VERSION_ATTRIBUTE) != null);
        }
    }
}
