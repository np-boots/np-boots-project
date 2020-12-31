package cn.np.boots.test.common;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.test.base.NpBaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class NpTypeUtilsTest extends NpBaseTest {

    @Test
    public void date(){
        Date date = new Date(1603175024307L);

        //format
        Assert.assertEquals("2020-10-20 14:23:44",NpUtils.type().date().format(date));
        Assert.assertEquals("2020-10-20",NpUtils.type().date().format(date,"yyyy-MM-dd"));

        //tryParse
        Assert.assertNotNull(NpUtils.type().date().tryParse("2020-10-20 14:23:44"));
        Assert.assertNotNull(NpUtils.type().date().tryParse("2020-10-20"));
    }
}
