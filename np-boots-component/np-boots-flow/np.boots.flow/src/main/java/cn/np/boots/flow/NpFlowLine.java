package cn.np.boots.flow;

public interface NpFlowLine<Node,In,Event, Ctx> extends NpFlowEngineComponent {

    Event getEvent();

    Node getFrom();

    Node getTo();

    boolean isSatisfied(In in, Ctx ctx);

    void action(Node from, Node to, Event event, In input, Ctx ctx);
}
