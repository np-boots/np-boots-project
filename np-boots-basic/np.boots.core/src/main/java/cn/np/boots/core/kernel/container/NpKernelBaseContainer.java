package cn.np.boots.core.kernel.container;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.core.kernel.common.NpKernelConstants;
import cn.np.boots.core.kernel.container.context.NpKernelContainerApplicationContext;
import cn.np.boots.core.kernel.container.lifecycle.ContainerApplicationContextRefreshed;
import cn.np.boots.core.kernel.container.lifecycle.ContainerApplicationContextStarted;
import cn.np.boots.core.kernel.container.lifecycle.ContainerBeanFactoryPostProcessor;
import cn.np.boots.core.kernel.container.lifecycle.ContainerBeanPostProcessor;
import cn.np.boots.core.kernel.root.NpKernelRoot;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class NpKernelBaseContainer implements NpKernelContainer {
    protected final NpKernelRoot root;
    protected final NpKernelContainerProperties containerProperties;

    public NpKernelBaseContainer(NpKernelRoot root, NpKernelContainerProperties containerProperties) {
        this.root = root;
        this.containerProperties = containerProperties;
    }

    @Override
    public String containerName() {
        return this.containerProperties.getAppName();
    }

    @Override
    public NpKernelRoot getRoot() {
        return this.root;
    }

    @Override
    public NpKernelContainerProperties getContainerProperties() {
        return this.containerProperties;
    }

    @Override
    public NpKernelContainer initialize() {

        final AnnotationConfigApplicationContext rootApplicationContext = this.getRoot().getRuntime().getApplicationContext();
        final NpKernelContainerApplicationContext containerApplicationContext = this.getRuntime().getApplicationContext();


        if (!NpUtils.type().string().isEmpty(containerProperties.getAppName())) {
            containerApplicationContext.getSpringApplicationContext().setDisplayName(containerProperties.getAppName());
        }

        // loader
        CachedIntrospectionResults.acceptClassLoader(containerApplicationContext.getSpringApplicationContext().getClassLoader());

        // profile
        if (!NpUtils.type().string().isEmpty(containerProperties.getActiveProfiles())) {
            String[] activeProfiles = containerProperties.getActiveProfiles().split(NpKernelConstants.PROFILE_SEPARATOR);
            containerApplicationContext.getSpringApplicationContext().getEnvironment().setActiveProfiles(activeProfiles);
        } else if (!NpUtils.type().collection().isEmpty(rootApplicationContext.getEnvironment().getActiveProfiles())) {
            containerApplicationContext.getSpringApplicationContext().getEnvironment().setActiveProfiles(rootApplicationContext.getEnvironment().getActiveProfiles());
        } else {
            containerApplicationContext.getSpringApplicationContext().getEnvironment().setActiveProfiles(NpKernelConstants.DEFAULT_PROFILE_VALUE);
        }

        // lifecycle-listener
        containerApplicationContext.getSpringApplicationContext().addApplicationListener(new ContainerApplicationContextStarted(this));
        containerApplicationContext.getSpringApplicationContext().addApplicationListener(new ContainerApplicationContextRefreshed(this));
        containerApplicationContext.getSpringApplicationContext().addBeanFactoryPostProcessor(new ContainerBeanFactoryPostProcessor(this));
        containerApplicationContext.getSpringApplicationContext().getBeanFactory().addBeanPostProcessor(new ContainerBeanPostProcessor(this));

        this.doInitialize();
        return this;
    }

    @Override
    public NpKernelContainer refresh() {
        this.getRuntime().getApplicationContext().getSpringApplicationContext().refresh();
        return this;
    }

    @Override
    public NpKernelContainer start() {
        this.getRuntime().getApplicationContext().getSpringApplicationContext().start();
        return this;
    }

    @Override
    public NpKernelContainer shutdown() {
        return this;
    }

    protected abstract NpKernelContainer doInitialize();
}
