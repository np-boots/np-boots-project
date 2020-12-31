package cn.np.boots.common.pattern;

import cn.np.boots.common.pattern.get.NpGetPattern;

public class NpPattern {

    private static final NpGetPattern get = new NpGetPattern();

    public static NpGetPattern get() {
        return get;
    }
}
