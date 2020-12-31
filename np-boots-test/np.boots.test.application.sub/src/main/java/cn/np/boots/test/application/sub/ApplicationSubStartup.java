package cn.np.boots.test.application.sub;

import cn.np.boots.core.NpBootsApplication;
import cn.np.boots.rpc.core.EnableNpBootsRpc;
import org.springframework.context.annotation.ComponentScan;

@EnableNpBootsRpc
@NpBootsApplication
@ComponentScan("cn.np")
public class ApplicationSubStartup {
}
