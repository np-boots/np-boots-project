package cn.np.boots.test;

import cn.np.boots.core.NpBootsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@NpBootsApplication
//@SpringBootApplication
@ComponentScan("cn.np")
public class Startup {
    public static void main(String[] args) {
        SpringApplication.run(Startup.class);
    }
}
