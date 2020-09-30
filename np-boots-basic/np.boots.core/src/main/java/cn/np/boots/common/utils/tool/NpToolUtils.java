package cn.np.boots.common.utils.tool;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Accessors(fluent = true)
public class NpToolUtils {
    private final NpNetToolUtils tool = new NpNetToolUtils();
}
