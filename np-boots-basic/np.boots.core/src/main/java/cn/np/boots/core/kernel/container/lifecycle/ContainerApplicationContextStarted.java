package cn.np.boots.core.kernel.container.lifecycle;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

public class ContainerApplicationContextStarted implements ApplicationListener {

    private final NpKernelContainer container;
    public ContainerApplicationContextStarted(NpKernelContainer container){
        this.container = container;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextStartedEvent) {
            System.out.println(((ContextRefreshedEvent) event).getApplicationContext().getDisplayName() + ":ContainerApplicationContextStarted");
        }
    }
}
