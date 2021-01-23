package cn.np.boots.statemachine;

import cn.np.boots.statemachine.api.NpStateMachineTransitionAction;

public interface NpStateMachineBuilder<State, Transition, Event, Input, Ctx> {
    NpStateMachine<State, Transition, Event, Input, Ctx> build(String machineId);

    NpStateMachineTransitionAction.Init<State, Transition, Event, Input, Ctx> transition();
}
