package cn.np.boots.statemachine.standard;

import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NpStandardStateMachine<S, E, C> implements NpStateMachine<S, E, C> {
    protected String machineId;
    protected final Map<S, NpStateMachineState<S, E, C>> stateMap = new HashMap<>();

    @Override
    public S fire(S source, E event, C ctx) {
//        if (!ready) {
//            throw new StateMachineException("State machine is not ready");
//        }

        NpStateMachineState sourceState = this.assertGet(source);

        Optional<StateMachineTransition<S, E, C>> transition = sourceState.getTransition(event);
        if (transition.isPresent()) {
            return transition.get().transit(ctx).getId();
        }

        return source;
    }

    @Override
    public void verify() throws NpStateMachineException {

    }


    protected NpStateMachineState<S, E, C> getOrCreate(S stateId) {
        NpStateMachineState<S, E, C> state = stateMap.get(stateId);
        if (state == null) {
            state = new StateMachineStateModel<>(stateId);
            stateMap.put(stateId, state);
        }
        return state;
    }

    protected StateMachineState<S, E, C> assertGet(S stateId) {
        StateMachineState<S, E, C> state = stateMap.get(stateId);
        StateMachineAssertUtils.notNull(state, () -> stateId + " is not found, please check state machine");
        return state;
    }
}
