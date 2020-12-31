package cn.np.boots.statemachine.standard;

import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;
import cn.np.boots.statemachine.utils.NpStateMachineAssertUtils;

import java.util.*;

public class NpStandardStateMachineState<State, Event, Ctx> implements NpStateMachineState<State, Event, Ctx> {
    protected final State stateId;

    private Map<Event, NpStateMachineTransition<State, Event, Ctx>> transitions = new HashMap<>();

    public NpStandardStateMachineState(State stateId) {
        NpStateMachineAssertUtils.notNull(stateId, () -> "StateMachineStateModel's init:" + stateId + " can not be null");
        this.stateId = stateId;
    }

    @Override
    public State getId() {
        return this.stateId;
    }

    @Override
    public NpStateMachineState<State, Event, Ctx> bindTransition(NpStateMachineTransition<State, Event, Ctx> transition) {
        NpStateMachineTransition existingTransition = transitions.get(transition.getEvent());
        if (existingTransition != null) {
            if (existingTransition.equals(transition)) {
                throw new NpStateMachineException(existingTransition + " already Exist, you can not add another one");
            }
        }

//        transition.setSource(this);
//        transition.setTarget(transition.getTarget());
//        transition.setEvent(transition.getEvent());
//        transition.setType(transition.getType());

        transitions.put(transition.getEvent(), transition);
        return this;
    }

    @Override
    public Optional<NpStateMachineTransition<State, Event, Ctx>> getTransition(Event event) {
        return Optional.ofNullable(this.transitions.get(event));
    }

    @Override
    public Collection<NpStateMachineTransition<State, Event, Ctx>> getTransitions() {
        return this.transitions.values();
    }

    @Override
    public void verify() throws NpStateMachineException {
        if (stateId == null) {
            throw new NpStateMachineException("StateMachineState's stateId can not be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NpStandardStateMachineState<?, ?, ?> that = (NpStandardStateMachineState<?, ?, ?>) o;
        return stateId.equals(that.stateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId);
    }

    @Override
    public String toString() {
        return stateId.toString();
    }
}
