package cn.np.boots.flow;

public interface NpFlowInput<Node, In, Event, Ctx> {
    Node getTargetFlowId();

    In getInput();

    Event getEvent();

    Ctx getContext();
}
