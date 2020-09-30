package cn.np.boots.core.kernel.container.lifecycle;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContainerApplicationContextRefreshed implements ApplicationListener {

    private final NpKernelContainer container;
    public ContainerApplicationContextRefreshed(NpKernelContainer container){
        this.container = container;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent){
            System.out.println(((ContextRefreshedEvent) event).getApplicationContext().getDisplayName() + ":ContainerApplicationContextRefreshed");
        }
    }
}
