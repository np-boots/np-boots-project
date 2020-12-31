package cn.np.boots.flow;

public interface NpFlowRelation<Id, In, Ctx> extends NpFlowEngineComponent {
    Id getFrom();

    boolean isSatisfied(In in, Ctx ctx);

    Id getTo();
}
