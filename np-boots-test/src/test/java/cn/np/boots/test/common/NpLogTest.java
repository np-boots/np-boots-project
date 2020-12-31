package cn.np.boots.test.common;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.test.base.NpBaseTest;
import org.junit.Test;

public class NpLogTest extends NpBaseTest {

    @Test
    public void debug(){
        NpUtils.log().debug().msg("***********:this a debug message");
    }

    @Test
    public void info(){
        NpUtils.log().info().msg("***********:this a info message");
    }

    @Test
    public void lazy(){
        NpUtils.log().trace().msg(()->{
            System.out.println("**********XXXXXXXXXXXXXXXXXXXXXX0");
            return "this is lazy log";
        });
    }
}
