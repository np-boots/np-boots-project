package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineBuilder;
import cn.np.boots.statemachine.mealy.NpMealyStateMachine;
import cn.np.boots.statemachine.mealy.builder.NpMealyTransitionBuilder;

public class NpStateMachineStructureBuilder<S, T, E, I, C> implements NpStateMachineBuilder<S, T, E, I, C> {
    private final NpMealyStateMachine<S, T, E, I, C> stateMachine = new NpMealyStateMachine<S, T, E, I, C>();

    @Override
    public NpStateMachineTransitionAction.Init<S,T, E, I, C> transition() {
        return new NpMealyTransitionBuilder<S,T, E, I, C>(this.stateMachine);
    }

    @Override
    public NpStateMachine<S,T, E, I, C> build(String machineId) {
        return stateMachine;
    }
}
