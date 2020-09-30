package cn.np.boots.core.kernel.common;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public abstract class NpAnnotationScanner<T extends Annotation, TRepeatable extends Annotation> {
    protected Class<T> annotationClass;
    protected Class<TRepeatable> annotationRepeatableClass;

    public NpAnnotationScanner(Class<T> annotationClass, Class<TRepeatable> annotationRepeatableClass) {
        this.annotationClass = annotationClass;
        this.annotationRepeatableClass = annotationRepeatableClass;
    }

    public NpAnnotationScanner scan(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        annotationMetadata.getAnnotations().stream(this.annotationClass).forEach(annotation -> {
            this.process(annotation, registry, importBeanNameGenerator, annotationMetadata);
        });
        annotationMetadata.getAnnotations().stream(this.annotationRepeatableClass).forEach(annotationRepeatable -> {
            MergedAnnotation<?>[] annotations = annotationRepeatable.getAnnotationArray("value", this.annotationClass);
            Stream.of(annotations).forEach(annotation -> {
                this.process(annotation, registry, importBeanNameGenerator, annotationMetadata);
            });
        });
        return this;
    }

    protected abstract void process(MergedAnnotation<?> annotation, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator, AnnotationMetadata annotationMetadata);
}
