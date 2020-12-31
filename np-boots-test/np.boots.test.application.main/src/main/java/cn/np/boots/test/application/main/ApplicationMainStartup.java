package cn.np.boots.test.application.main;

import cn.np.boots.core.NpBootsApplication;
import cn.np.boots.rpc.core.EnableNpBootsRpc;
import org.springframework.boot.SpringApplication;

@EnableNpBootsRpc
@NpBootsApplication
public class ApplicationMainStartup {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMainStartup.class);
    }
}
