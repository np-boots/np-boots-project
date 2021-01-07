package cn.np.boots.statemachine;

public interface NpStateMachine<State, Transition, Event, Input, Ctx> extends NpStateMachineSelfVerify {
    String getMachineId();

    State fire(State source, Event event, Input input, Ctx ctx);

    void storage(NpStateMachineStorage<State, Transition, Event, Input, Ctx> storage);

//    NpStateMachine<State, Transition, Event, Input, Ctx> registerTransition(NpStateMachineTransition<State, Transition, Event, Input, Ctx> transition);
}
