package cn.np.boots.statemachine;

public interface NpStateMachineCondition<Event, Ctx> {
    boolean isSatisfied(Event event, Ctx ctx);
}
