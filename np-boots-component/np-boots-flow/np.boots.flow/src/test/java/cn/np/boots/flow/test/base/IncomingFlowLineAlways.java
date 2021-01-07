package cn.np.boots.flow.test.base;

public class IncomingFlowLineAlways<In, Ctx> extends IncomingFlowLine<In, Ctx> {

    public IncomingFlowLineAlways(String from, String to) {
        super(from, to);
    }

    @Override
    public boolean isSatisfied(In in, Ctx ctx) {
        return true;
    }

    @Override
    public void action(String from, String to, String s, In input, Ctx ctx) {

    }
}
