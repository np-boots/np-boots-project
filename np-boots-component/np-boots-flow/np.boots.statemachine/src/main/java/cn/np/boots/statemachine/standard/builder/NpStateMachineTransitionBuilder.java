package cn.np.boots.statemachine.standard.builder;

import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.NpStateMachineTransitionAction;
import cn.np.boots.statemachine.standard.NpStandardStateMachineTransition;

public class NpStateMachineTransitionBuilder<State, Event, Ctx>
        implements NpStateMachineTransitionAction.Init<State, Event, Ctx>, NpStateMachineTransitionAction.From<State, Event, Ctx>,
        NpStateMachineTransitionAction.To<State, Event, Ctx>, NpStateMachineTransitionAction.On<State, Event, Ctx>, NpStateMachineTransitionAction.When<State, Event, Ctx> {

    private NpStateMachine<State, Event, Ctx> stateMachine;
    private State source;
    private State target;
    private Event event;
    private NpStateMachineCondition<Event, Ctx> condition;
    private NpStateMachineAction<State, Event, Ctx> action;

    public NpStateMachineTransitionBuilder(NpStateMachine<State, Event, Ctx> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public NpStateMachineTransitionAction.From<State, Event, Ctx> from(State stateId) {
        this.source = stateId;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.To<State, Event, Ctx> to(State stateId) {
        this.target = stateId;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.On<State, Event, Ctx> on(Event event) {
        this.event = event;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.When<State, Event, Ctx> when(NpStateMachineCondition<Event, Ctx> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public void perform(NpStateMachineAction<State, Event, Ctx> action) {
        this.action = action;
        NpStateMachineTransition<State, Event, Ctx> transition = this.build();
        stateMachine.registerTransition(transition);
    }

    protected NpStateMachineTransition build() {
        NpStateMachineTransition<State, Event, Ctx> transition = new NpStandardStateMachineTransition();
        transition
                .setSourceId(this.source)
                .setTargetId(this.target)
                .setEvent(this.event)
                .setCondition(this.condition)
                .setAction(this.action);
        return transition;
    }
}
