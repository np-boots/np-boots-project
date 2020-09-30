package cn.np.boots.core.kernel.root;

import lombok.Getter;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Getter
public class NpKernelRootRuntime {
    private final AnnotationConfigApplicationContext applicationContext;
    private final DefaultListableBeanFactory beanFactory;

    public NpKernelRootRuntime(AnnotationConfigApplicationContext applicationContext, final DefaultListableBeanFactory beanFactory) {
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }
}
