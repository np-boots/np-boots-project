package cn.np.boots.statemachine.mealy;

import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineStorage;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;
import cn.np.boots.statemachine.utils.NpStateMachineAssertUtils;

import java.util.List;

public class NpMealyStateMachine<State, Transition, Event, Input, Ctx> implements NpStateMachine<State, Transition, Event, Input, Ctx> {
    protected String machineId;
    protected NpStateMachineStorage<State, Transition, Event, Input, Ctx> storage;
    // protected final Map<State, NpStateMachineState<State,Transition, Event, Input, Ctx>> stateMap = new HashMap<>();

    @Override
    public String getMachineId() {
        return this.machineId;
    }

    @Override
    public void storage(NpStateMachineStorage<State, Transition, Event, Input, Ctx> storage) {
        this.storage = storage;
    }

    @Override
    public State fire(State source, Event event, Input input, Ctx ctx) {
        if (this.storage == null) {
            throw new NpStateMachineException("StateMachine is not ready: storage can not be null ");
        }

        NpStateMachineState<State, Transition, Event, Input, Ctx> sourceState = storage.assertLoadState(source);
        List<Transition> transitions = sourceState.transitions();
        if (!NpUtils.type().collection().isEmpty(transitions)) {
            for (Transition transition : transitions) {
                NpStateMachineTransition<State, Transition, Event, Input, Ctx> stateMachineTransition = this.storage.assertLoadTransition(transition);
                if (stateMachineTransition.isSatisfied(input, ctx)) {
                    return stateMachineTransition.getTargetId();
                }
            }
        }
        return source;
    }

//    @Override
//    public NpStateMachine<State, Event, Input, Ctx> registerTransition(NpStateMachineTransition<State, Event, Input, Ctx> transition) {
//        NpStateMachineAssertUtils.notNull(transition.getSourceId(), "registerTransition:StateMachineTransition's sourceId can not be null");
//        NpStateMachineAssertUtils.notNull(transition.getTargetId(), "registerTransition:StateMachineTransition's targetId can not be null");
//
//        transition.setSource(this.getOrCreate(transition.getSourceId()));
//        transition.setTarget(this.getOrCreate(transition.getTargetId()));
//
//        return this;
//    }

    @Override
    public void verify() throws NpStateMachineException {

    }


//    protected NpStateMachineState<State, Event, Input, Ctx> getOrCreate(State stateId) {
//        NpStateMachineState<State, Event, Input, Ctx> state = stateMap.get(stateId);
//        if (state == null) {
//            state = new NpMealyStateMachineState<>(stateId);
//            stateMap.put(stateId, state);
//        }
//        return state;
//    }
//
//    protected NpStateMachineState<State, Event, Input, Ctx> assertGet(State stateId) {
//        NpStateMachineState<State, Event, Input, Ctx> state = stateMap.get(stateId);
//        NpStateMachineAssertUtils.notNull(state, () -> stateId + " is not found, please check state machine");
//        return state;
//    }
}
