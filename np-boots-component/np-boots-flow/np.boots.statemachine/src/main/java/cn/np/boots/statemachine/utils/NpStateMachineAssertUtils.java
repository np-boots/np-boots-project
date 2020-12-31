package cn.np.boots.statemachine.utils;

import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;

import java.util.function.Supplier;

public class NpStateMachineAssertUtils {
    public static void notNull(Object value, String msg) {
        if (value == null)
            throw new NpStateMachineException(msg);
    }

    public static void notNull(Object value, Supplier<String> msg) {
        if (value == null)
            throw new NpStateMachineException(msg.get());
    }
}
