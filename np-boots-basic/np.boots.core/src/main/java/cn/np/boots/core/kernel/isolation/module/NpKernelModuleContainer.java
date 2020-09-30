package cn.np.boots.core.kernel.isolation.module;

import cn.np.boots.core.kernel.container.NpKernelBaseContainer;
import cn.np.boots.core.kernel.container.NpKernelContainer;
import cn.np.boots.core.kernel.container.NpKernelContainerProperties;
import cn.np.boots.core.kernel.container.beanfactory.NpKernelContainerBeanFactory;
import cn.np.boots.core.kernel.container.context.NpKernelContainerApplicationContext;
import cn.np.boots.core.kernel.container.loader.NpKernelContainerClassLoader;
import cn.np.boots.core.kernel.container.runtime.NpKernelContainerDefaultRuntime;
import cn.np.boots.core.kernel.container.runtime.NpKernelContainerRuntime;
import cn.np.boots.core.kernel.root.NpKernelRoot;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NpKernelModuleContainer extends NpKernelBaseContainer {

    private final NpKernelContainerRuntime runtime;

    public NpKernelModuleContainer(NpKernelRoot root, NpKernelContainerProperties containerProperties) {
        super(root, containerProperties);
        this.runtime = this.buildRuntime();
    }

    private NpKernelContainerDefaultRuntime buildRuntime() {
        ConfigurableApplicationContext rootApplicationContext = root.getRuntime().getApplicationContext();
        DefaultListableBeanFactory rootBeanFactory = root.getRuntime().getBeanFactory();


        ClassLoader springClassLoader = rootApplicationContext.getClassLoader();
        NpKernelContainerClassLoader classLoader = new NpKernelContainerClassLoader(springClassLoader);

        DefaultListableBeanFactory springBeanFactory = new DefaultListableBeanFactory(rootBeanFactory);
        springBeanFactory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        NpKernelContainerBeanFactory beanFactory = new NpKernelContainerBeanFactory(springBeanFactory);


        AnnotationConfigApplicationContext springApplicationContext = new AnnotationConfigApplicationContext(springBeanFactory);
        springApplicationContext.setClassLoader(springClassLoader);
        springApplicationContext.getBeanFactory().setBeanClassLoader(springClassLoader);

        NpKernelContainerApplicationContext applicationContext = new NpKernelContainerApplicationContext(springApplicationContext);

        return new NpKernelContainerDefaultRuntime(this, classLoader, applicationContext, beanFactory);
    }

    @Override
    protected NpKernelContainer doInitialize() {
        this.runtime.getApplicationContext().getSpringApplicationContext();
        return this;
    }

    @Override
    public NpKernelContainerRuntime getRuntime() {
        return this.runtime;
    }
}
