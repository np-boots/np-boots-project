package cn.np.boots.flow.exception;

public class NpFlowRuntimeException extends NpFlowException {
    public NpFlowRuntimeException(String msg) {
        super(msg);
    }

    public NpFlowRuntimeException(Exception e) {
        super(e);
    }

    public NpFlowRuntimeException(Exception e, String msg) {
        super(e, msg);
    }
}
