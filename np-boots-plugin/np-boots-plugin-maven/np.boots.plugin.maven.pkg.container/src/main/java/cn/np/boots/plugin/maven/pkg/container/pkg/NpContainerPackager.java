package cn.np.boots.plugin.maven.pkg.container.pkg;

import cn.np.boots.plugin.maven.pkg.container.common.ArkUtils;
import cn.np.boots.plugin.maven.pkg.container.common.ZipUtils;
import cn.np.boots.plugin.maven.pkg.container.libraries.Libraries;
import cn.np.boots.plugin.maven.pkg.container.libraries.Library;
import cn.np.boots.plugin.maven.pkg.container.libraries.LibraryScope;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class NpContainerPackager {
    private static final String ARK_VERSION_ATTRIBUTE = "Sofa-Ark-Version";

    private final File source;
    private File executableFatJar;
    private File pluginModuleJar;
    private boolean packageProvided;
    private Library arkContainerLibrary = null;

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
                }
            }
        });
    }

    private boolean alreadyRepackaged() throws IOException {
        try (JarFile jarFile = new JarFile(this.source)) {
            Manifest manifest = jarFile.getManifest();
            return (manifest != null && manifest.getMainAttributes().getValue(ARK_VERSION_ATTRIBUTE) != null);
        }
    }
}
