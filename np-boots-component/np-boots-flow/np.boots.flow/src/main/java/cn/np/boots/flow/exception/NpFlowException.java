package cn.np.boots.flow.exception;

import cn.np.boots.exception.NpRuntimeException;

public class NpFlowException extends NpRuntimeException {
    public NpFlowException(String msg) {
        super(msg);
    }

    public NpFlowException(Exception e) {
        super(e);
    }

    public NpFlowException(Exception e, String msg) {
        super(e, msg);
    }
}
