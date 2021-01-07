package cn.np.boots.flow.standard.node;

import cn.np.boots.flow.NpFlowNode;
import cn.np.boots.flow.NpFlowLine;

import java.util.HashSet;
import java.util.Set;

public abstract class NpStandardFlowNode<Node, In, Out, Event, Ctx> implements NpFlowNode<Node, In, Out, Event, Ctx> {

    private final Node flowId;
    private final Set<NpFlowLine<Node, In, Event, Ctx>> lines = new HashSet<>();

    public NpStandardFlowNode(Node flowId) {
        this.flowId = flowId;
    }

    @Override
    public Node getFlowId() {
        return this.flowId;
    }

    @Override
    public Set<NpFlowLine<Node, In, Event, Ctx>> getLines() {
        return this.lines;
    }


    protected void addRelation(NpFlowLine<Node, In, Event, Ctx> relation) {
        this.lines.add(relation);
    }
}
