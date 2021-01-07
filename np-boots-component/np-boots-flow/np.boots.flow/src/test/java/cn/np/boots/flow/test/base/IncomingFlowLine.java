package cn.np.boots.flow.test.base;

import cn.np.boots.flow.standard.relation.NpStandardFlowLine;

public abstract class IncomingFlowLine<In,Ctx> extends NpStandardFlowLine<String, In, String, Ctx> implements IncomingFlowConstants {

    public IncomingFlowLine(String from, String to) {
        super(from, EVENT_INCOMING, to);
    }
}
