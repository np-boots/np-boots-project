package cn.np.boots.statemachine.standard;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;
import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineTransition;
import lombok.Data;

@Data
public class NpStandardStateMachineTransition<State, Event, Ctx> implements NpStateMachineTransition<State, Event, Ctx> {
    private State sourceId;

    private NpStateMachineState<State, Event, Ctx> source;

    private State targetId;

    private NpStateMachineState<State, Event, Ctx> target;

    private Event event;

    private NpStateMachineCondition<Event, Ctx> condition;

    private NpStateMachineAction<State, Event, Ctx> action;

    @Override
    public final String toString() {
        return source + "-[" + event.toString() + "]->" + target;
    }

    @Override
    public NpStateMachineState<State, Event, Ctx> transit(Event event, Ctx ctx) {
        this.verify();
        if (condition == null || condition.isSatisfied(event, ctx)) {
            if (action != null) {
                action.execute(source.getId(), target.getId(), event, ctx);
            }
            return target;
        }
        return source;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof NpStandardStateMachineTransition) {
            NpStandardStateMachineTransition other = (NpStandardStateMachineTransition) anObject;
            if (this.event.equals(other.getEvent())
                    && this.source.equals(other.getSource())
                    && this.target.equals(other.getTarget())) {
                return true;
            }
        }
        return false;
    }
}
