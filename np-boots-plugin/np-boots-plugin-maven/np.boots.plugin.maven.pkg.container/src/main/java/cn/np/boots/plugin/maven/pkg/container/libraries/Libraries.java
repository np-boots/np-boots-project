package cn.np.boots.plugin.maven.pkg.container.libraries;

import java.io.IOException;

public interface Libraries {
    void doWithLibraries(LibraryCallback callback) throws IOException;
}
