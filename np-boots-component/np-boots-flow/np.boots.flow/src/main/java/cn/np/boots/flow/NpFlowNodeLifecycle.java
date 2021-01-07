package cn.np.boots.flow;

public interface NpFlowNodeLifecycle<Node, In, Out, Event, Ctx> {
    default void onRegistered(NpFlowEngine<Node, In, Out, Event, Ctx> engine){

    }


}
