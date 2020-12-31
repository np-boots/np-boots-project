package cn.np.boots.common.utils.web.action;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class NpWebActionOption {
    private Long connectTimeoutMillSecond = 10L * 1000L;
    private Long readTimeoutMillSecond = 10L * 1000L;
    private Long writeTimeoutMilSecond = 10L * 1000L;

    public NpWebAction action() {
        return new NpWebAction(this);
    }
}
