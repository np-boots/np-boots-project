package cn.np.boots.test.common;

import cn.np.boots.common.utils.NpUtils;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class NpJsonSerializeUtilsTest {

    @Test
    public void serialize() {
        JsonData data = new JsonData();
        String json = NpUtils.serialize().json().action().serialize(data);
        System.out.println(json);
        //Assert.assertEquals(JsonData.jsonString, json);
    }

    @Test
    public void serializeCus(){
        JsonData data = new JsonData();
        String json = NpUtils.serialize().json().option().isWriteDateAsTimestamp(false).serializeDateFormat("EEE MMM dd hh:mm:ss z yyyy")
                .action().serialize(data);
        System.out.println(json);
    }


    @Test
    public void deserialize() {
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":1603175024307}", JsonData.class));
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":\"1603175024307\"}", JsonData.class));
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":\"2020-10-20\"}", JsonData.class));
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":\"2020-10-20 23:14:14\"}", JsonData.class));
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":\"2020-10-20T23:14:14.261+0800\"}", JsonData.class));
        System.out.println( NpUtils.serialize().json().action().deserialize("{\"integer\":123,\"date\":\"星期二 十月 20 02:23:44 GMT+08:00 2020\"}", JsonData.class));
    }
}

@Data
class JsonData {
    public static final String jsonString = "{\"integer\":123,\"date\":1603175024307}";
    private Integer integer = 123;
    private Date date = new Date(1603175024307L);
}
