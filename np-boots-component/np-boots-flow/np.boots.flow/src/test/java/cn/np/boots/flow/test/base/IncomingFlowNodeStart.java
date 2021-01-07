package cn.np.boots.flow.test.base;

import cn.np.boots.flow.NpFlowOutput;

public class IncomingFlowNodeStart<In, Out, Ctx> extends IncomingFlowNode<In, Out, Ctx> implements IncomingFlowConstants {


    private String nextNodeId;

    public IncomingFlowNodeStart(String next) {
        super(NODE_START);
        this.addRelation(new IncomingFlowLineAlways(this.getFlowId(),next));
    }

    @Override
    public NpFlowOutput<String, Out> execute(String s, In in, Ctx ctx) {
        return null;
    }
}
