package cn.np.boots.common.utils.type;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

@Getter
@Accessors(fluent = true)
public class NpStringUtils {
    public Boolean isNotEmpty(String value){
        return !isEmpty(value);
    }
    public Boolean isEmpty(String value){
        return StringUtils.isEmpty(value);
    }
}
