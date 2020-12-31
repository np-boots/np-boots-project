package cn.np.boots.statemachine;

import java.util.Collection;
import java.util.Optional;

public interface NpStateMachineState<State, Event, Ctx> extends NpStateMachineSelfVerify {
    State getId();

    NpStateMachineState<State, Event, Ctx> bindTransition(NpStateMachineTransition<State, Event, Ctx> transition);

    Optional<NpStateMachineTransition<State, Event, Ctx>> getTransition(Event event);

    Collection<NpStateMachineTransition<State, Event, Ctx>> getTransitions();
}
