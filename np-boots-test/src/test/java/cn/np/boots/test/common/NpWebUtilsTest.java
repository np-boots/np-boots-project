package cn.np.boots.test.common;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.common.utils.web.action.NpWebActionResponse;
import cn.np.boots.common.utils.web.action.NpWebAsyncActionCallback;
import cn.np.boots.test.base.NpBaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class NpWebUtilsTest extends NpBaseTest {

    @Test
    public void post() throws IOException {
        System.out.println(NpUtils.web().action().post().url("http://www.baidu.com").sync().getBody());
    }

    @Test
    public void cusGet() throws IOException {
        NpUtils.web().actionOption().connectTimeoutMillSecond(10L).action().post().url("http://www.baidu.com").sync().getBody();
    }

    @Test
    public void get() throws IOException {
        System.out.println(NpUtils.web().action().post().url("http://www.baidu.com").sync().getBody());
    }

    @Test
    public void async() throws Exception {
        NpUtils.web().action().get().url("http://www.baidu.com").async(new NpWebAsyncActionCallback() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onResponse(NpWebActionResponse response) {
                System.out.println(response.getBody());
            }
        });

        Thread.sleep(5 * 1000);
    }
}
