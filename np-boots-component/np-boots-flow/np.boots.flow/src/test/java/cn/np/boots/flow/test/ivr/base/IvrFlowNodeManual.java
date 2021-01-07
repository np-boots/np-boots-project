package cn.np.boots.flow.test.ivr.base;

import cn.np.boots.flow.NpFlowOutput;
import cn.np.boots.flow.test.base.IncomingFlowNode;

public class IvrFlowNodeManual extends IncomingFlowNode<IvrFlowInput,IvrFlowOutput,IvrFlowContext> {
    private String skillGroup;

    public IvrFlowNodeManual(String skillGroup, String flowId) {
        super(flowId);
        this.skillGroup = skillGroup;
    }

    @Override
    public NpFlowOutput<String, IvrFlowOutput> execute(String s, IvrFlowInput ivrFlowInput, IvrFlowContext ivrFlowContext) {
        return NpFlowOutput.success(this.getFlowId(), new IvrFlowOutput("转人工:" + this.skillGroup));
    }


}
