package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachineBuilder;
import cn.np.boots.statemachine.standard.NpStandardStateMachine;

public class NpStateMachineStructureBuilder<S, E, C> implements NpStateMachineBuilder<S, E, C> {
    private final NpStandardStateMachine<S,E,C> stateMachine = new NpStandardStateMachine<S, E, C>();

    public NpStateMachineTransitionAction.Init<S, E, C> transition() {
        return new StateMachineTransitionBuilder(this.stateMachine);
    }
}
