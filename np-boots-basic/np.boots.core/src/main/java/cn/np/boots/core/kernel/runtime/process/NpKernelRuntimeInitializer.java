package cn.np.boots.core.kernel.runtime.process;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class NpKernelRuntimeInitializer implements ApplicationContextInitializer {

    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        NpKernelRuntimeInitializer.applicationContext = applicationContext;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
