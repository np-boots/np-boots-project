//package cn.np.boots.core.kernal.runtime.root.lifecycle.process;
//
//import cn.np.boots.common.utils.NpUtils;
//import cn.np.boots.core.kernal.common.NpAnnotationScanner;
//import cn.np.boots.core.kernal.runtime.root.NpRuntimeRootContext;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanNameGenerator;
//import org.springframework.core.type.AnnotationMetadata;
//
//import java.util.stream.Stream;
//
//public class NpRuntimeRootLifecycleProcessor {
//    private final NpRuntimeRootContext rootContext;
//
//    public NpRuntimeRootLifecycleProcessor(NpRuntimeRootContext rootContext) {
//        this.rootContext = rootContext;
//    }
//
//    public void process(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
//        NpAnnotationScanner[] scanners = { new NpRuntimeRootLifecycleAnnotationProcessor(rootContext)  };
//
//        NpUtils.log().debug().surround("NpRuntimeRootLifecycle scan", () -> {
//            Stream.of(scanners).forEach(processor -> {
//                processor.scan(annotationMetadata, registry, importBeanNameGenerator);
//            });
//        });
//    }
//}
