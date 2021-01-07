package cn.np.boots.statemachine.mealy;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;
import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;
import lombok.Data;

@Data
public class NpMealyStateMachineTransition<State, Event, Input, Ctx> implements NpStateMachineTransition<State, Event, Input, Ctx> {
    private State sourceId;

    private NpStateMachineState<State, Event, Input, Ctx> source;

    private State targetId;

    private NpStateMachineState<State, Event, Input, Ctx> target;

    private Event event;

    private NpStateMachineCondition<Input, Ctx> condition;

    private NpStateMachineAction<State, Event, Input, Ctx> action;

    @Override
    public final String toString() {
        return source + "-[" + event.toString() + "]->" + target;
    }

    @Override
    public void verify() throws NpStateMachineException {

    }

    public void setSource(NpStateMachineState<State, Event, Input, Ctx> source) {
        this.source = source;
        this.source.bindTransition(this);
    }

    @Override
    public boolean isSatisfied(Input input, Ctx ctx) {
        return condition.isSatisfied(input,ctx);
    }

    //    @Override
//    public NpStateMachineState<State, Event, Input, Ctx> transit(Input input, Ctx ctx) {
//        this.verify();
//        if (condition == null || condition.isSatisfied(input, ctx)) {
//            if (action != null) {
//                action.execute(source.getId(), target.getId(), event, input, ctx);
//            }
//            return target;
//        }
//        return source;
//    }

//    @Override
//    public boolean equals(Object anObject) {
//        if (anObject instanceof NpStandardStateMachineTransition) {
//            NpStandardStateMachineTransition other = (NpStandardStateMachineTransition) anObject;
//            if (this.event.equals(other.getEvent())
//                    && this.source.equals(other.getSource())
//                    && this.target.equals(other.getTarget())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
