package cn.np.boots.plugin.maven.pkg.container.libraries;

import java.io.File;

public class Library {

    private final String  name;

    private final File file;

    private LibraryScope  scope;

    private final boolean unpackRequired;

    public Library(File file, LibraryScope scope) {
        this(file, scope, false);
    }

    public Library(File file, LibraryScope scope, boolean unpackRequired) {
        this(null, file, scope, unpackRequired);
    }

    public Library(String name, File file, LibraryScope scope, boolean unpackRequired) {
        this.name = (name == null ? file.getName() : name);
        this.file = file;
        this.scope = scope;
        this.unpackRequired = unpackRequired;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public LibraryScope getScope() {
        return scope;
    }

    public void setScope(LibraryScope scope) {
        this.scope = scope;
    }

    public boolean isUnpackRequired() {
        return unpackRequired;
    }
}
