package cn.np.boots.core.kernel.runtime2.root2;

import cn.np.boots.core.kernel.runtime2.root2.lifecycle.event.NpRuntimeLifecycleEventHub;
import lombok.Getter;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

@Getter
public class NpRuntimeRootContext {
    private final ConfigurableApplicationContext applicationContext;
    private final DefaultListableBeanFactory beanFactory;
    private final NpRuntimeLifecycleEventHub lifecycleEventHub = new NpRuntimeLifecycleEventHub();

    public NpRuntimeRootContext(ConfigurableApplicationContext applicationContext, DefaultListableBeanFactory beanFactory) {
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }
}
