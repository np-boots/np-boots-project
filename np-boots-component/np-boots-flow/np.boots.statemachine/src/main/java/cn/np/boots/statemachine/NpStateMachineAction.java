package cn.np.boots.statemachine;

public interface NpStateMachineAction<State,Event,Ctx> {
    void execute(State from, State to, Event event, Ctx ctx);
}
