package cn.np.boots.flow;

public interface NpFlowEngineComponent<In, Ctx> {
    void register(FlowEngine<NpFlowEngineComponent<In, Ctx>, NpFlowEngineComponent<In, Ctx>,In, Ctx> engine);
}
