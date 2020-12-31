package cn.np.boots.test.base;

import cn.np.boots.core.NpBootsApplication;
import cn.np.boots.core.kernel.runtime.NpKernelRuntimeHub;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {NpBaseTest.class})
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"cn.np.*"})
@Import(NpKernelRuntimeHub.class)
public class NpBaseTest {
}
