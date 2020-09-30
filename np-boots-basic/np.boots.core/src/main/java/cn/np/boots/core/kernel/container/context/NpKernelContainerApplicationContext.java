package cn.np.boots.core.kernel.container.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NpKernelContainerApplicationContext {

    private AnnotationConfigApplicationContext springApplicationContext;

    public NpKernelContainerApplicationContext(AnnotationConfigApplicationContext springApplicationContext) {
        this.springApplicationContext = springApplicationContext;
    }

    public AnnotationConfigApplicationContext getSpringApplicationContext() {
        return this.springApplicationContext;
    }
}
