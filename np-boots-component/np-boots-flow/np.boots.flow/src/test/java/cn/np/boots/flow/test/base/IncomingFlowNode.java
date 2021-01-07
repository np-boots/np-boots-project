package cn.np.boots.flow.test.base;

import cn.np.boots.flow.standard.node.NpStandardFlowNode;

public abstract class IncomingFlowNode<In, Out, Ctx> extends NpStandardFlowNode<String, In, Out, String, Ctx> {
    public IncomingFlowNode(String flowId) {
        super(flowId);
    }
}
