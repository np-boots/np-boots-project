package cn.np.boots.core.kernel.root;

import cn.np.boots.core.kernel.container.NpKernelContainer;
import cn.np.boots.core.kernel.container.NpKernelContainerProperties;
import cn.np.boots.core.kernel.isolation.module.NpKernelModuleContainer;
import cn.np.boots.core.kernel.isolation.standard.NpKernelStandardContainer;

public class NpKernelRoot {

    private final NpKernelRootRuntime runtime;
    private NpKernelContainer main;

    public NpKernelRoot(NpKernelRootRuntime runtime) {
        this.runtime = runtime;
    }

    public NpKernelRootRuntime getRuntime() {
        return this.runtime;
    }

    public void start() {
        NpKernelContainerProperties containerProperties = new NpKernelContainerProperties();
        this.main = new NpKernelStandardContainer(this,containerProperties);
        NpKernelContainer moduleContainer = new NpKernelModuleContainer(this,containerProperties);
        moduleContainer.initialize().refresh().start();
    }

}
