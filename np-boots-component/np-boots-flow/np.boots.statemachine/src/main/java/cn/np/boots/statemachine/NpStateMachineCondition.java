package cn.np.boots.statemachine;

@FunctionalInterface
public interface NpStateMachineCondition<Input, Ctx> {
    boolean isSatisfied(Input input, Ctx ctx);
}
