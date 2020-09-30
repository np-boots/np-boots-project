package cn.np.boots.core.kernel.container.spi;

import java.util.Iterator;

public interface NpKernelContainerServiceSPI {
    <T> Iterator<T> serviceLoad(Class<T> clazz);
}
