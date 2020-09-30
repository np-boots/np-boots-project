//package cn.np.boots.core.kernel.runtime2.root2;
//
//import cn.np.boots.common.utils.NpUtils;
//import cn.np.boots.core.api.listener.NpOnBeanDefinitionEvent;
//import cn.np.boots.core.kernel.common.NpAnnotationScanner;
//import cn.np.boots.core.kernel.runtime2.root2.lifecycle.event.NpRuntimeLifecycleEventHub;
//import cn.np.boots.core.kernel.runtime2.root2.lifecycle.process.NpLifecycleAnnotationScanProcessor;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.BeanFactoryAware;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanNameGenerator;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
//import org.springframework.core.type.AnnotationMetadata;
//import java.util.stream.Stream;
//
//@Slf4j
//@Getter
//public class NpRuntimeRootHub implements ImportBeanDefinitionRegistrar, BeanFactoryAware, ApplicationContextAware {
//    private ConfigurableApplicationContext applicationContext;
//    private DefaultListableBeanFactory beanFactory;
//    private NpRuntimeRootContext rootContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = (ConfigurableApplicationContext)applicationContext;
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
//    }
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
//        rootContext = new NpRuntimeRootContext(applicationContext, beanFactory);
//        ConfigurationPropertiesBindingPostProcessor.register(registry);
//
//        NpAnnotationScanner[] scanners = {
//                new NpLifecycleAnnotationScanProcessor(rootContext)
//        };
//
//        NpUtils.log().debug().surround("NpRuntimeRootLifecycle scan", () -> {
//            Stream.of(scanners).forEach(processor -> {
//                processor.scan(annotationMetadata, registry, importBeanNameGenerator);
//            });
//        });
//
//        NpRuntimeLifecycleEventHub lifecycleEventHub = rootContext.getLifecycleEventHub();
//
//        NpOnBeanDefinitionEvent beanDefinitionEvent = new NpOnBeanDefinitionEvent(annotationMetadata, registry, importBeanNameGenerator);
//        lifecycleEventHub.notifyOnBeanDefinitionCallback(beanDefinitionEvent);
//
//        this.applicationContext.addBeanFactoryPostProcessor(beanFactory->{
//
//        });
//
//        this.applicationContext.addApplicationListener();
//    }
//}
