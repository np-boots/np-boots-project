package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineBuilder;
import cn.np.boots.statemachine.mealy.NpMealyStateMachine;
import cn.np.boots.statemachine.mealy.builder.NpMealyTransitionBuilder;

public class NpStateMachineStructureBuilder<S, E, I, C> implements NpStateMachineBuilder<S, E, I, C> {
    private final NpMealyStateMachine<S, E, I, C> stateMachine = new NpMealyStateMachine<S, E, I, C>();

    @Override
    public NpStateMachineTransitionAction.Init<S, E,I, C> transition() {
        return new NpMealyTransitionBuilder<S, E, I, C>(this.stateMachine);
    }

    @Override
    public NpStateMachine<S, E, I, C> build(String machineId) {
        return stateMachine;
    }
}
