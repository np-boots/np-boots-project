package cn.np.boots.common.utils.reflect;

import cn.np.boots.common.utils.NpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public class NpReflectUtils {
    public Class<?> classForName(String name, @Nullable ClassLoader classLoader) {
        try {
            return ClassUtils.forName(name, classLoader);
        } catch (Throwable e) {
            NpUtils.log().debug().msg(e,"Class.ForName error");
            return null;
        }
    }

    public boolean isCglibProxyClass(Class<?> clazz){
        return ClassUtils.isCglibProxyClass(clazz);
    }
}
