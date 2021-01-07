package cn.np.boots.statemachine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface NpStateMachineState<State,Transition> extends NpStateMachineSelfVerify {
    State getId();

    List<Transition> transitions();

//    NpStateMachineState<State, Event, Input, Ctx> bindTransition(NpStateMachineTransition<State, Event, Input, Ctx> transition);
//
//    Optional<List<NpStateMachineTransition<State, Event, Input, Ctx>>> getTransition(Event event);
}
