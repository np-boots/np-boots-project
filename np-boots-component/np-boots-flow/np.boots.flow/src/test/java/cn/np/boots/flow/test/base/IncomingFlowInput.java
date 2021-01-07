package cn.np.boots.flow.test.base;

import cn.np.boots.flow.NpFlowInput;

public class IncomingFlowInput<In, Ctx> implements NpFlowInput<String, In, String, Ctx>, IncomingFlowConstants {

    private final String targetFlowId;
    private final In input;
    private final Ctx ctx;

    public IncomingFlowInput(String targetFlowId,In input,Ctx ctx){
        this.targetFlowId = targetFlowId;
        this.input = input;
        this.ctx = ctx;
    }

    @Override
    public String getTargetFlowId() {
        return this.targetFlowId;
    }

    @Override
    public In getInput() {
        return this.input;
    }

    @Override
    public String getEvent() {
        return EVENT_INCOMING;
    }

    @Override
    public Ctx getContext() {
        return this.ctx;
    }
}
