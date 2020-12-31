package cn.np.boots.flow;

import java.util.Set;

public interface NpFlowNode<Id,In, Ctx> extends NpFlowEngineComponent<In,Ctx> {
    Id getFlowId();

    Set<NpFlowRelation<Id,In, Ctx>> getRelations();
}
