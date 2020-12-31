package cn.np.boots.test.application.sub.rpc.provider;

import cn.np.boots.rpc.core.NpServiceProvider;
import cn.np.boots.test.application.sub.rpc.api.ITestRpcService;

@NpServiceProvider
public class TestRpcServiceProvider implements ITestRpcService {
    @Override
    public String say(String msg) {
        return "you say:" + msg;
    }
}
