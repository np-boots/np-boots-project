package cn.np.boots.common.utils.log;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NpLogUtils {
    private final NpLogAction trace = new NpLogActionTrace();
    private final NpLogAction debug = new NpLogActionDebug();
    private final NpLogAction info = new NpLogActionInfo();
    private final NpLogAction waring = new NpLogActionWaring();
    private final NpLogAction error = new NpLogActionError();
}
