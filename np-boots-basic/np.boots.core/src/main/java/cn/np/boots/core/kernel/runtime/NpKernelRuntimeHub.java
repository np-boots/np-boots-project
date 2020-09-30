package cn.np.boots.core.kernel.runtime;

import cn.np.boots.core.kernel.root.NpKernelRoot;
import cn.np.boots.core.kernel.root.NpKernelRootRuntime;
import cn.np.boots.core.kernel.runtime.process.NpKernelRuntimeInitializer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

@Component
public class NpKernelRuntimeHub implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        AnnotationConfigApplicationContext rootApplicationContext = (AnnotationConfigApplicationContext)NpKernelRuntimeInitializer.getApplicationContext();

        NpKernelRootRuntime rootRuntime = new NpKernelRootRuntime(rootApplicationContext, beanFactory);

        NpKernelRoot root = new NpKernelRoot(rootRuntime);
        root.start();
    }
}
