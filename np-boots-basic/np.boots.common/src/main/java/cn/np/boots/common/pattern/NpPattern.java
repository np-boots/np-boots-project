package cn.np.boots.common.pattern;

import cn.np.boots.common.pattern.asserts.NpAssertsPattern;
import cn.np.boots.common.pattern.get.NpGetPattern;

public class NpPattern {

    private static final NpGetPattern get = new NpGetPattern();
    private static final NpAssertsPattern asserts = new NpAssertsPattern();

    public static NpGetPattern get() {
        return get;
    }

    public static NpAssertsPattern asserts() {
        return asserts;
    }
}
