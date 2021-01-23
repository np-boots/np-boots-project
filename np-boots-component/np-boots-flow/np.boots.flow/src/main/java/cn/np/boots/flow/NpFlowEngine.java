package cn.np.boots.flow;

import java.util.Set;

public interface NpFlowEngine<Node, In, Out, Event, Ctx> {
    void relation(NpFlowLine<Node, In, Event, Ctx> relation);

    default void relation(Set<NpFlowLine<Node, In, Event, Ctx>> relations) {
        relations.forEach(relation -> {
            this.relation(relation);
        });
    }

    void node(NpFlowNode<Node, In, Out, Event, Ctx> node);

    default void nodes(Set<NpFlowNode<Node, In, Out, Event, Ctx>> nodes) {
        nodes.forEach(node -> {
            this.node(node);
        });
    }

    void start();

    NpFlowOutput<Node, Out> execute(NpFlowInput<Node, In, Event, Ctx> input) ;

    void plugin(NpFlowEnginePlugin<Node, In, Out, Event, Ctx> plugin);
}
