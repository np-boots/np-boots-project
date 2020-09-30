package cn.np.boots.plugin.maven.pkg.container.libraries;

public interface LibraryScope {
    LibraryScope COMPILE   = new LibraryScope() {
        @Override
        public String toString() {
            return "compile";
        }
    };

    LibraryScope RUNTIME   = new LibraryScope() {

        @Override
        public String toString() {
            return "runtime";
        }

    };

    LibraryScope PROVIDED  = new LibraryScope() {

        @Override
        public String toString() {
            return "provided";
        }

    };

    LibraryScope PLUGIN    = new LibraryScope() {

        @Override
        public String toString() {
            return "plugin";
        }

    };

    LibraryScope MODULE    = new LibraryScope() {

        @Override
        public String toString() {
            return "module";
        }

    };

    LibraryScope CONTAINER = new LibraryScope() {

        @Override
        public String toString() {
            return "container";
        }

    };
}
