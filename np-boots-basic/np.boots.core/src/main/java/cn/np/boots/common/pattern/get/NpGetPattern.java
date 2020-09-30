package cn.np.boots.common.pattern.get;

import cn.np.boots.common.utils.NpUtils;

import java.util.function.Supplier;

public class NpGetPattern {
    public String getStringOrEmptyElse(String value,String defaultValue){
        if(NpUtils.type().string().isEmpty(value)){
            return defaultValue;
        }
        return value;
    }

    public <T> T getOrElse(Supplier<T> tryCode, T exceptionValue) {
        try {
            return tryCode.get();
        } catch (Throwable e) {
            // ignore
            return exceptionValue;
        }
    }
}
