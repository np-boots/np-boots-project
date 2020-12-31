package cn.np.boots.statemachine.api.expcetion;

import cn.np.boots.exception.NpRuntimeException;

public class NpStateMachineException extends NpRuntimeException {
    public NpStateMachineException(String msg) {
        super(msg);
    }
}
