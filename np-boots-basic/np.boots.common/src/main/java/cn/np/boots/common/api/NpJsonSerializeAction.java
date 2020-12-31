package cn.np.boots.common.api;

import java.util.Locale;
import java.util.TimeZone;

public interface NpJsonSerializeAction {
    Locale defaultLocal = Locale.SIMPLIFIED_CHINESE;
    TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT+08:00");
    String defaultFormatPattern = "yyyy-MM-dd HH:mm:ss";
    String[] defaultParsePattern = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "EEE MMM dd hh:mm:ss z yyyy"
    };

    String serialize(Object data);
    <T> T deserialize(String content, Class<T> target, Class<?>... elements);
}
