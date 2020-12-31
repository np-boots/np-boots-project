package cn.np.boots.rpc.core;

import cn.np.boots.core.api.listener.NpBootsLifecycleListener;
import cn.np.boots.core.api.listener.NpOnBeanDefinitionEvent;
import org.springframework.core.type.AnnotationMetadata;

public class NpBootsRpcLifecycle implements NpBootsLifecycleListener {

    @Override
    public void onBeanDefinition(NpOnBeanDefinitionEvent event) {
       final AnnotationMetadata annotationMetadata = event.getAnnotationMetadata();

        annotationMetadata.getAnnotations().stream(NpServiceProvider.class).forEach(x->{
            System.out.println(x);
        });
    }
}
