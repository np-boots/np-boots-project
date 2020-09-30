package cn.np.boots.core.kernel.container.runtime;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import cn.np.boots.core.kernel.container.beanfactory.NpKernelContainerBeanFactory;
import cn.np.boots.core.kernel.container.context.NpKernelContainerApplicationContext;
import cn.np.boots.core.kernel.container.loader.NpKernelContainerClassLoader;
import cn.np.boots.core.kernel.container.spi.NpKernelContainerServiceSPI;
import cn.np.boots.core.kernel.root.NpKernelRootRuntime;

public interface NpKernelContainerRuntime {

    NpKernelContainer getContainer();

    NpKernelRootRuntime getRootRuntime();

    NpKernelContainerClassLoader getClassLoader();

    NpKernelContainerApplicationContext getApplicationContext();

    NpKernelContainerBeanFactory getBeanFactory();

    NpKernelContainerServiceSPI getServiceSPI();
}
