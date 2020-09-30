package cn.np.boots.common.utils.web;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Accessors(fluent = true)
public class NpWebUtils {
    private final NpWebActionUtils action = new NpWebActionUtils();
}
