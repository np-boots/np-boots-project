package cn.np.boots.plugin.maven.pkg.container.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarWriter implements LoaderClassesWriter {

    private final JarOutputStream jarOutput;
    private final Set<String> writtenEntries = new HashSet<>();

    public JarWriter(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        this.jarOutput = new JarOutputStream(fileOutputStream);
    }

    public void writeManifest(final Manifest manifest) throws IOException {
        JarEntry entry = new JarEntry("META-INF/MANIFEST.mf");

    }

    private void writeEntry(JarEntry entry, EntryWriter entryWriter) throws IOException {
        String parent = entry.getName();
        if (parent.endsWith("/")) {
            parent = parent.substring(0, parent.length() - 1);
        }
        if (parent.lastIndexOf("/") != -1) {
            parent = parent.substring(0, parent.lastIndexOf("/") + 1);
            if (parent.length() > 0) {
                writeEntry(new JarEntry(parent), null);
            }
        }
    }

    private interface EntryWriter {
        void write(OutputStream outputStream) throws IOException;
    }
}
