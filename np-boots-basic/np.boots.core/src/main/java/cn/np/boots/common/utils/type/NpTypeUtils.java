package cn.np.boots.common.utils.type;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Accessors(fluent = true)
public class NpTypeUtils {
    private final NpStringUtils string = new NpStringUtils();
    private final NpCollectionUtils collection = new NpCollectionUtils();
}
