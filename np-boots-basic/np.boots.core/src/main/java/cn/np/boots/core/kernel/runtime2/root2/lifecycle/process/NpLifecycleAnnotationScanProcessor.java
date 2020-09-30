package cn.np.boots.core.kernel.runtime2.root2.lifecycle.process;

import cn.np.boots.common.pattern.NpPattern;
import cn.np.boots.core.api.listener.EnableNpBootsLifecycleListener;
import cn.np.boots.core.api.listener.EnableNpBootsLifecycleListenerRepeatable;
import cn.np.boots.core.api.listener.NpBootsLifecycleListener;
import cn.np.boots.core.kernel.common.NpAnnotationScanner;
import cn.np.boots.core.kernel.common.NpNameGenerator;
import cn.np.boots.core.kernel.runtime2.root2.NpRuntimeRootContext;
import cn.np.boots.core.kernel.runtime2.root2.lifecycle.event.NpRuntimeLifecycleCallback;
import cn.np.boots.core.kernel.runtime2.root2.lifecycle.event.NpRuntimeLifecycleEventHub;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotationMetadata;

public class NpLifecycleAnnotationScanProcessor extends NpAnnotationScanner<EnableNpBootsLifecycleListener, EnableNpBootsLifecycleListenerRepeatable> {
    private final NpRuntimeRootContext rootContext;
    private final DefaultListableBeanFactory beanFactory;
    private final NpRuntimeLifecycleEventHub lifecycleEventHub;
    private final ConfigurationPropertiesBindingPostProcessor configurationPropertiesBindingPostProcessor;
    private final AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor;
    private final NpNameGenerator npNameGenerator = NpNameGenerator.prefix("NpRuntimeRootLifecycleBean#");

    public NpLifecycleAnnotationScanProcessor(NpRuntimeRootContext rootContext){
        super(EnableNpBootsLifecycleListener.class,EnableNpBootsLifecycleListenerRepeatable.class);
        this.rootContext = rootContext;
        this.beanFactory = rootContext.getBeanFactory();
        this.lifecycleEventHub = rootContext.getLifecycleEventHub();
        this.configurationPropertiesBindingPostProcessor = beanFactory.getBean(ConfigurationPropertiesBindingPostProcessor.class);
        this.autowiredAnnotationBeanPostProcessor = beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class);
    }

    @Override
    protected void process(MergedAnnotation<?> annotation, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator, AnnotationMetadata annotationMetadata) {
        Class<NpBootsLifecycleListener> listenerClass = (Class<NpBootsLifecycleListener>) annotation.getClass(EnableNpBootsLifecycleListener.PROPERTY_LIFECYCLE);
        String listenerBeanName = NpPattern.get().getStringOrEmptyElse(EnableNpBootsLifecycleListener.PROPERTY_Name, npNameGenerator.generate(listenerClass));

        Object listenerBean = null;
        if (!beanFactory.containsBean(listenerBeanName)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(listenerClass);
//                            beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            beanFactory.registerBeanDefinition(listenerBeanName, beanDefinition);
            listenerBean = beanFactory.getBean(listenerBeanName);
            configurationPropertiesBindingPostProcessor.postProcessBeforeInitialization(listenerBean, listenerBeanName);
            autowiredAnnotationBeanPostProcessor.postProcessBeforeInitialization(listenerBean, listenerBeanName);
        } else {
            listenerBean = beanFactory.getBean(listenerBeanName);
        }


        NpRuntimeLifecycleCallback callback = NpRuntimeLifecycleCallback.builder()
                .order(annotation.getInt(EnableNpBootsLifecycleListener.PROPERTY_ORDER))
                .annotationMetadata(annotationMetadata)
                .registry(registry)
                .importBeanNameGenerator(importBeanNameGenerator)
                .annotation(annotation)
                .annotationClass(EnableNpBootsLifecycleListener.class)
                .callbackActionBeanClass(listenerClass)
                .callbackActionBeanName(listenerBeanName)
                .callbackBean(listenerBean)
                .build();
        lifecycleEventHub.registerOnBeanDefinitionCallback(callback)
                .registerOnBeanFactoryPostCallback(callback)
                .registerOnBeanPostCallback(callback)
                .registerOnApplicationStartedCallback(callback);
    }
}
