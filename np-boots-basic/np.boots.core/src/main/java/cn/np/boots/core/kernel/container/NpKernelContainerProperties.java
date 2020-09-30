package cn.np.boots.core.kernel.container;

import lombok.Data;

@Data
public class NpKernelContainerProperties {
    private String appName;
    private String activeProfiles;
    private Boolean isAllowBeanDefinitionOverriding = false;
    private Class<?> startup;
}
