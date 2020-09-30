package cn.np.boots.core.kernel.isolation.standard;

import cn.np.boots.core.kernel.container.NpKernelBaseContainer;
import cn.np.boots.core.kernel.container.NpKernelContainer;
import cn.np.boots.core.kernel.container.NpKernelContainerProperties;
import cn.np.boots.core.kernel.container.beanfactory.NpKernelContainerBeanFactory;
import cn.np.boots.core.kernel.container.context.NpKernelContainerApplicationContext;
import cn.np.boots.core.kernel.container.loader.NpKernelContainerClassLoader;
import cn.np.boots.core.kernel.container.runtime.NpKernelContainerDefaultRuntime;
import cn.np.boots.core.kernel.container.runtime.NpKernelContainerRuntime;
import cn.np.boots.core.kernel.root.NpKernelRoot;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NpKernelStandardContainer extends NpKernelBaseContainer {

    private final NpKernelContainerRuntime runtime;

    public NpKernelStandardContainer(NpKernelRoot root, NpKernelContainerProperties containerProperties) {
        super(root, containerProperties);
        this.runtime = this.buildRuntime();
    }

    private NpKernelContainerRuntime buildRuntime() {
        AnnotationConfigApplicationContext rootContext = root.getRuntime().getApplicationContext();

        NpKernelContainerApplicationContext applicationContext = new NpKernelContainerApplicationContext(rootContext);
        NpKernelContainerBeanFactory beanFactory = new NpKernelContainerBeanFactory(rootContext.getBeanFactory());
        NpKernelContainerClassLoader classLoader = new NpKernelContainerClassLoader(rootContext.getClassLoader());

        return new NpKernelContainerDefaultRuntime(this, classLoader, applicationContext, beanFactory);
    }

    @Override
    protected NpKernelContainer doInitialize() {
        return this;
    }

    @Override
    public NpKernelContainerRuntime getRuntime() {
        return this.runtime;
    }
}
