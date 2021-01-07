package cn.np.boots.flow;

public interface NpFlowEnginePlugin<Node, In, Out, Event, Ctx> {

    default int orderBy() {
        return 0;
    }

    default void onStarted() {

    }

    default void onExecuting(NpFlowInput<Node, In, Event, Ctx> input,Node node) {

    }

    default void onExecuted(NpFlowInput<Node, In, Event, Ctx> input, NpFlowOutput<Node, Out> output) {

    }
}
