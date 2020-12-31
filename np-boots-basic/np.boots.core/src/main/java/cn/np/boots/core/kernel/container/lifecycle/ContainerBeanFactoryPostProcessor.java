package cn.np.boots.core.kernel.container.lifecycle;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ContainerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private final NpKernelContainer container;
    public ContainerBeanFactoryPostProcessor(NpKernelContainer container){
        this.container = container;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
       // System.out.println(this.container.getRuntime().getApplicationContext().getSpringApplicationContext().getDisplayName()+ ":ContainerBeanFactoryPostProcessor");
    }
}
