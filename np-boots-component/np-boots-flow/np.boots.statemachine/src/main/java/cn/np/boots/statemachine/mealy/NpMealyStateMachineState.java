package cn.np.boots.statemachine.mealy;

import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;
import cn.np.boots.statemachine.utils.NpStateMachineAssertUtils;
import lombok.Data;

import java.util.*;

public class NpMealyStateMachineState<State,Transition> implements NpStateMachineState<State,Transition> {
    private State stateId;
    private List<Transition> transitions = new ArrayList<>();


    public NpMealyStateMachineState(){

    }

    @Override
    public State getId() {
        return this.stateId;
    }

    @Override
    public List<Transition> transitions() {
        return this.transitions;
    }

    @Override
    public void verify() throws NpStateMachineException {

    }

    //    private Map<Event, List<NpStateMachineTransition<State, Event, Input, Ctx>>> transitions = new HashMap<>();
//
//    public NpMealyStateMachineState(State stateId) {
//        NpStateMachineAssertUtils.notNull(stateId, () -> "StateMachineStateModel's init:" + stateId + " can not be null");
//        this.stateId = stateId;
//    }
//
//    @Override
//    public State getId() {
//        return this.stateId;
//    }
//
//    @Override
//    public NpStateMachineState<State, Event, Input, Ctx> bindTransition(NpStateMachineTransition<State, Event, Input, Ctx> transition) {
//        List<NpStateMachineTransition<State, Event, Input, Ctx>> existingTransition = transitions.get(transition.getEvent());
//        if (existingTransition != null) {
//            if(!existingTransition.contains(transition)){
//                existingTransition.add(transition);
//            }
//        }
//        else {
//            existingTransition = new ArrayList<>();
//            existingTransition.add(transition);
//            transitions.put(transition.getEvent(), existingTransition);
//        }
//        return this;
//    }
//
//    @Override
//    public Optional<List<NpStateMachineTransition<State, Event, Input, Ctx>>> getTransition(Event event) {
//        return Optional.ofNullable(this.transitions.get(event));
//    }
//
//    @Override
//    public void verify() throws NpStateMachineException {
//        if (stateId == null) {
//            throw new NpStateMachineException("StateMachineState's stateId can not be null");
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        NpMealyStateMachineState<?, ?, ?, ?> that = (NpMealyStateMachineState<?, ?, ?, ?>) o;
//        return stateId.equals(that.stateId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(stateId);
//    }
//
//    @Override
//    public String toString() {
//        return stateId.toString();
//    }
}
