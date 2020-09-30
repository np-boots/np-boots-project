package cn.np.boots.core.api.aware;

import cn.np.boots.core.kernel.runtime2.NpRuntimeManager;

public interface NpRuntimeAware {
    void setNpRuntimeManager(NpRuntimeManager runtimeManager);
}
