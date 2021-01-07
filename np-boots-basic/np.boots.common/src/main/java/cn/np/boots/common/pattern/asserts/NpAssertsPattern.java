package cn.np.boots.common.pattern.asserts;

import java.util.function.Supplier;

public class NpAssertsPattern {

    public <TValue> TValue notNull(TValue value, String msg, Object... args) throws IllegalArgumentException {
        if (value == null) {
            if (args == null || args.length == 0) {
                throw new IllegalArgumentException(msg);
            } else {
                throw new IllegalArgumentException(String.format(msg, args));
            }
        }
        return value;
    }

    public <TValue> TValue notNull(TValue value, Supplier<String> msg) {
        if(value == null){
            throw new IllegalArgumentException(msg.get());
        }
        return value;
    }
}
