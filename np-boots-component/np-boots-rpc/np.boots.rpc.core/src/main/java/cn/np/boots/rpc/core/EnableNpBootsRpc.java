package cn.np.boots.rpc.core;

import cn.np.boots.core.api.listener.EnableNpBootsLifecycleListener;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableNpBootsLifecycleListener(lifecycle = NpBootsRpcLifecycle.class)
public @interface EnableNpBootsRpc {

}
