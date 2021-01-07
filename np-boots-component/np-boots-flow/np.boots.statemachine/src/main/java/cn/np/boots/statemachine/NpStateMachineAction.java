package cn.np.boots.statemachine;

@FunctionalInterface
public interface NpStateMachineAction<State, Event, Input, Ctx> {
    void execute(State from, State to, Event event, Input input, Ctx ctx);
}
