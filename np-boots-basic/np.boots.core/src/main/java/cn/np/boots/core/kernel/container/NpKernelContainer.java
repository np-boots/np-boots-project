package cn.np.boots.core.kernel.container;

import cn.np.boots.core.kernel.container.runtime.NpKernelContainerRuntime;
import cn.np.boots.core.kernel.root.NpKernelRoot;

public interface NpKernelContainer {
    String containerName();
    NpKernelRoot getRoot();
    NpKernelContainerProperties getContainerProperties();
    NpKernelContainerRuntime getRuntime();

    NpKernelContainer initialize();
    NpKernelContainer refresh();
    NpKernelContainer start();
    NpKernelContainer shutdown();
}
