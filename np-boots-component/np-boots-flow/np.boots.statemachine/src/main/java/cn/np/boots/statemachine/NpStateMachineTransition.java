package cn.np.boots.statemachine;

public interface NpStateMachineTransition<State, Trans, Event, Input, Ctx>
        extends NpStateMachineCondition<Input, Ctx>, NpStateMachineSelfVerify {

    Trans getTransitionId();

    State getSourceId();

    void setSourceId(State stateId);

    NpStateMachineState<State, Event, Input, Ctx> getSource();

    void setSource(NpStateMachineState<State, Event, Input, Ctx> state);

    Event getEvent();

    void setEvent(Event event);

    State getTargetId();

    void setTargetId(State stateId);

    NpStateMachineState<State, Event, Input, Ctx> getTarget();

    void setTarget(NpStateMachineState<State, Event, Input, Ctx> state);

//    NpStateMachineCondition<Event, Ctx> getCondition();
//
//    void setCondition(NpStateMachineCondition<Event, Ctx> condition);
//
//    NpStateMachineAction<State, Event, Ctx> getAction();
//
//    void setAction(NpStateMachineAction<State, Event, Ctx> action);

//    NpStateMachineState<State, Event, Input, Ctx> transit(Input input, Ctx ctx);
}
