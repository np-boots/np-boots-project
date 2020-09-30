package cn.np.boots.core.kernel.container.lifecycle;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ContainerBeanPostProcessor implements BeanPostProcessor {
    private final NpKernelContainer container;
    public ContainerBeanPostProcessor(NpKernelContainer container){
        this.container = container;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

       // System.out.println(container.getRuntime().getApplicationContext().getDisplayName()+":ContainerBeanPostProcessor");

        return bean;
    }
}

