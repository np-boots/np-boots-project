package cn.np.boots.common.utils.serialize.json;

import cn.np.boots.common.utils.NpUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Setter
@Accessors(fluent = true)
public class NpJacksonJsonSerializeOption {
    private static final TimeZone beiJingtimeZone = TimeZone.getTimeZone("GMT+08:00");

    /**
     * 序列化是否包含所有字段 默认为true
     */
    private boolean isSerializationInclusionAll = true;

    /**
     * 如果是空对象的时候,抛出异常 默认为false
     */
    private boolean isFailedOnEmptyBean = false;

    /**
     * 如果出现了未知属性,抛出异常 默认为false
     */
    private boolean isFailedOnUnknownProperty;

    /**
     * 是否java.util.Date自动转换为时间戳
     * 默认为true(默认Date以时间戳形式输出)
     */
    private boolean isWriteDateAsTimestamp = true;

    /**
     * java.util.Date的输出格式，仅当 isCancelWriteDateAsTimestamp = false 生效
     */
    private String serializeDateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * java.util.Date的输入格式
     */
    private String[] deserializeDateFormat = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "EEE MMM dd hh:mm:ss z yyyy"
    };

    public NpJacksonJsonSerializeActionAction action() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer() {
            @Override
            public java.util.Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                Long longValue = p.getValueAsLong();
                if (longValue == null || longValue == 0L) {
                    return NpUtils.type().date().tryParse(p.getText(), deserializeDateFormat);
                } else {
                    return new Date(longValue);
                }
            }
        });

        objectMapper.registerModule(javaTimeModule);

        if (this.isSerializationInclusionAll) {
            //序列化的时候序列对象的所有属性
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        }
        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, this.isFailedOnEmptyBean);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        if (!this.isWriteDateAsTimestamp) {
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
            javaTimeModule.addSerializer(Date.class, new DateSerializer() {
                @Override
                public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                    jsonGenerator.writeString(NpUtils.type().date().format(value, serializeDateFormat));
                }
            });
        }

        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, isFailedOnUnknownProperty);

        NpJacksonJsonSerializeActionAction action = new NpJacksonJsonSerializeActionAction(objectMapper);
        return action;
    }
}
