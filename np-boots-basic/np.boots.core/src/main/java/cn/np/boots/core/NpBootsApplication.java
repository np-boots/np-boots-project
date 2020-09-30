package cn.np.boots.core;

import cn.np.boots.core.kernel.runtime.NpKernelRuntimeHub;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(NpKernelRuntimeHub.class)
@SpringBootApplication
public @interface NpBootsApplication {

}
