package cn.np.boots.statemachine;

import cn.np.boots.statemachine.api.NpStateMachineTransitionAction;

public interface NpStateMachineBuilder<State, Event, Input, Ctx> {
    NpStateMachine<State, Event, Input, Ctx> build(String machineId);

    NpStateMachineTransitionAction.Init<State, Event,Input, Ctx> transition();
}
