package cn.np.boots.flow.standard.relation;

import cn.np.boots.flow.NpFlowLine;

public abstract class NpStandardFlowLine<Node, In, Event, Ctx> implements NpFlowLine<Node, In, Event, Ctx> {

    private Node from;
    private Event event;
    private Node to;

    public NpStandardFlowLine(Node from, Event event, Node to) {
        this.from = from;
        this.event = event;
        this.to = to;
    }

    @Override
    public Node getFrom() {
        return from;
    }

    @Override
    public Node getTo() {
        return to;
    }

    @Override
    public Event getEvent() {
        return this.event;
    }
}
