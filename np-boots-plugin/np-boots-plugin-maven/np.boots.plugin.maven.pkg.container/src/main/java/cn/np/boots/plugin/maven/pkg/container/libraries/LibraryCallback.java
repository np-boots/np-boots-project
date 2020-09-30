package cn.np.boots.plugin.maven.pkg.container.libraries;

import java.io.IOException;

public interface LibraryCallback {
    void library(Library library) throws IOException;
}
