package cn.np.boots.core.kernel.container.loader;

public class NpKernelContainerClassLoader{

    private final ClassLoader loader;

    public NpKernelContainerClassLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public ClassLoader getLoader(){
        return this.loader;
    }
}
