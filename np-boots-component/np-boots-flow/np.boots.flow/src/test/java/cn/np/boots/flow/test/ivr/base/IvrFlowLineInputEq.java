package cn.np.boots.flow.test.ivr.base;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.flow.test.base.IncomingFlowLine;

public class IvrFlowLineInputEq extends IncomingFlowLine<IvrFlowInput, IvrFlowContext> {
    private String input;

    public IvrFlowLineInputEq(String input, String from, String to) {
        super(from, to);
        this.input = input;
    }

    @Override
    public boolean isSatisfied(IvrFlowInput ivrFlowInput, IvrFlowContext ivrFlowContext) {
        if (ivrFlowInput != null && NpUtils.type().string().isNotEmpty(ivrFlowInput.getInput())) {
            return ivrFlowInput.getInput().equals(this.input);
        }
        return false;
    }

    @Override
    public void action(String from, String to, String s, IvrFlowInput input, IvrFlowContext ivrFlowContext) {
        System.out.println(String.format("running IvrFlowRelationInputEq=> from:{} to:{} input:{}", from, to, input));
    }
}
