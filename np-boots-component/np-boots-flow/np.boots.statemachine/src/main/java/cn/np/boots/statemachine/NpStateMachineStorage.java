package cn.np.boots.statemachine;

import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;

public interface NpStateMachineStorage<State, Transition, Event, Input, Ctx> {

    default NpStateMachineState<State, Transition, Event, Input, Ctx> assertLoadState(State state) {
        NpStateMachineState<State, Transition, Event, Input, Ctx> stateMachineState = this.loadState(state);
        if (stateMachineState == null) {
            throw new NpStateMachineException("StateMachine storage can not load state : " + state.toString());
        }
        return stateMachineState;
    }

    NpStateMachineState<State, Transition, Event, Input, Ctx> loadState(State state);

    default NpStateMachineTransition<State, Transition, Event, Input, Ctx> assertLoadTransition(Transition transition){
        NpStateMachineTransition<State, Transition, Event, Input, Ctx> stateMachineTransition = this.loadTransition(transition);
        if(stateMachineTransition == null){
            throw new NpStateMachineException("StateMachine storage can not load transition : " + transition.toString());
        }
        return stateMachineTransition;
    }

    NpStateMachineTransition<State, Transition, Event, Input, Ctx> loadTransition(Transition transition);
}
