package cn.np.boots.statemachine;

public interface NpStateMachine<State, Event, Ctx> extends NpStateMachineSelfVerify {
    String getMachineId();

    State fire(State source, Event event, Ctx ctx);

    NpStateMachine<State, Event, Ctx> registerTransition(NpStateMachineTransition<State, Event, Ctx> transition);
}
