package cn.np.boots.core.kernel.container.isolation;

import cn.np.boots.core.kernel.container.runtime.NpKernelContainerRuntime;
import cn.np.boots.core.kernel.root.NpKernelRoot;

public interface NpKernelContainerIsolationStrategy {

    NpKernelContainerIsolationStrategy setRoot(NpKernelRoot root);

    NpKernelContainerRuntime getRuntime();
}
