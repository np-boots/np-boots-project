package cn.np.boots.core.kernel.container.beanfactory;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class NpKernelContainerBeanFactory {
    private final ConfigurableListableBeanFactory beanFactory;

    public NpKernelContainerBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
