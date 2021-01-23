package cn.np.boots.statemachine.mealy.builder;

import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.NpStateMachineTransitionAction;
import cn.np.boots.statemachine.mealy.NpMealyStateMachineTransition;

public class NpMealyTransitionBuilder<State,Transition, Event,Input, Ctx>
        implements NpStateMachineTransitionAction.Init<State, Event,Input, Ctx>, NpStateMachineTransitionAction.From<State, Event,Input, Ctx>,
        NpStateMachineTransitionAction.To<State, Event,Input, Ctx>, NpStateMachineTransitionAction.On<State, Event,Input, Ctx>, NpStateMachineTransitionAction.When<State, Event,Input, Ctx> {

    private NpStateMachine<State, Event,Input, Ctx> stateMachine;
    private State source;
    private State target;
    private Event event;
    private NpStateMachineCondition<Input, Ctx> condition;
    private NpStateMachineAction<State, Event,Input, Ctx> action;

    public NpMealyTransitionBuilder(NpStateMachine<State, Event,Input, Ctx> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public NpStateMachineTransitionAction.From<State, Event,Input, Ctx> from(State stateId) {
        this.source = stateId;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.To<State, Event,Input, Ctx> to(State stateId) {
        this.target = stateId;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.On<State, Event,Input, Ctx> on(Event event) {
        this.event = event;
        return this;
    }

    @Override
    public NpStateMachineTransitionAction.When<State, Event,Input, Ctx> when(NpStateMachineCondition<Input, Ctx> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public void perform(NpStateMachineAction<State, Event,Input, Ctx> action) {
        this.action = action;
        NpStateMachineTransition<State, Event,Input, Ctx> transition = this.build();
        stateMachine.registerTransition(transition);
    }

    protected NpStateMachineTransition build() {
        NpMealyStateMachineTransition transition = new NpMealyStateMachineTransition();
        transition.setSourceId(this.source);
        transition.setTargetId(this.target);
        transition.setEvent(this.event);
        transition.setCondition(this.condition);
        transition.setAction(this.action);
        return transition;
    }
}
