package cn.np.boots.flow;

import java.util.Set;

public interface NpFlowNode<Node, In, Out, Event, Ctx> extends NpFlowEngineComponent<In, Ctx> {
    Node getFlowId();

    Set<NpFlowLine<Node, In, Event, Ctx>> getLines();

    NpFlowOutput<Node,Out> execute(Event event, In in, Ctx ctx);
}
