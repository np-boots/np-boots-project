package cn.np.boots.statemachine;

public interface NpStateMachineTransition<State, Event, Ctx> extends NpStateMachineSelfVerify  {

    State getSourceId();

    NpStateMachineTransition<State, Event, Ctx> setSourceId(State stateId);

    NpStateMachineState<State, Event, Ctx> getSource();

    NpStateMachineTransition<State, Event, Ctx> setSource(NpStateMachineState<State, Event, Ctx> state);

    Event getEvent();

    NpStateMachineTransition<State, Event, Ctx> setEvent(Event event);

    State getTargetId();

    NpStateMachineTransition<State, Event, Ctx> setTargetId(State stateId);

    NpStateMachineState<State, Event, Ctx> getTarget();

    NpStateMachineTransition<State, Event, Ctx> setTarget(NpStateMachineState<State, Event, Ctx> state);

    NpStateMachineCondition<Event, Ctx> getCondition();

    NpStateMachineTransition<State, Event, Ctx> setCondition(NpStateMachineCondition<Event, Ctx> condition);

    NpStateMachineAction<State, Event, Ctx> getAction();

    NpStateMachineTransition<State, Event, Ctx> setAction(NpStateMachineAction<State, Event, Ctx> action);

    NpStateMachineState<State, Event, Ctx> transit(Event event, Ctx ctx);
}
