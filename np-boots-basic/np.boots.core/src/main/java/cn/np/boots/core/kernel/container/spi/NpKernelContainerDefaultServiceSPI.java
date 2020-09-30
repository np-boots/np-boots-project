package cn.np.boots.core.kernel.container.spi;

import cn.np.boots.core.kernel.container.loader.NpKernelContainerClassLoader;

import java.util.Iterator;
import java.util.ServiceLoader;

public class NpKernelContainerDefaultServiceSPI implements NpKernelContainerServiceSPI {

    private final NpKernelContainerClassLoader classLoader;
    public NpKernelContainerDefaultServiceSPI(NpKernelContainerClassLoader classLoader){
        this.classLoader = classLoader;
    }
    @Override
    public <T> Iterator<T> serviceLoad(Class<T> clazz) {
        return ServiceLoader.load(clazz,classLoader.getLoader()).iterator();
    }
}
