package cn.np.boots.core.kernel.container.runtime;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import cn.np.boots.core.kernel.container.beanfactory.NpKernelContainerBeanFactory;
import cn.np.boots.core.kernel.container.context.NpKernelContainerApplicationContext;
import cn.np.boots.core.kernel.container.loader.NpKernelContainerClassLoader;
import cn.np.boots.core.kernel.container.spi.NpKernelContainerDefaultServiceSPI;
import cn.np.boots.core.kernel.container.spi.NpKernelContainerServiceSPI;
import cn.np.boots.core.kernel.root.NpKernelRootRuntime;

public class NpKernelContainerDefaultRuntime implements NpKernelContainerRuntime {

    private final NpKernelContainer container;
    private final NpKernelContainerClassLoader classLoader;
    private final NpKernelContainerApplicationContext applicationContext;
    private final NpKernelContainerBeanFactory beanFactory;
    private final NpKernelContainerServiceSPI serviceSPI;

    public NpKernelContainerDefaultRuntime(NpKernelContainer container, NpKernelContainerClassLoader classLoader,
                                           NpKernelContainerApplicationContext applicationContext,
                                           NpKernelContainerBeanFactory beanFactory) {
        this.container = container;
        this.classLoader = classLoader;
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
        this.serviceSPI = new NpKernelContainerDefaultServiceSPI(this.classLoader);
    }

    @Override
    public NpKernelContainer getContainer() {
        return this.container;
    }

    @Override
    public NpKernelRootRuntime getRootRuntime() {
        return this.container.getRoot().getRuntime();
    }

    @Override
    public NpKernelContainerClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Override
    public NpKernelContainerApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public NpKernelContainerBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public NpKernelContainerServiceSPI getServiceSPI() {
        return serviceSPI;
    }
}
