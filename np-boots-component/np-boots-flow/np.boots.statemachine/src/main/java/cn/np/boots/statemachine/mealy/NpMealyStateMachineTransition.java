package cn.np.boots.statemachine.mealy;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;
import cn.np.boots.statemachine.NpStateMachineState;
import cn.np.boots.statemachine.NpStateMachineTransition;
import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;
import lombok.Data;

@Data
public class NpMealyStateMachineTransition<State, Trans, Event, Input, Ctx> implements NpStateMachineTransition<State, Trans, Event, Input, Ctx> {

    private Trans transitionId;

    private State sourceId;

    private State targetId;

    private Event event;

    private NpStateMachineCondition<Input, Ctx> condition;

    private NpStateMachineAction<State, Event, Input, Ctx> action;


    @Override
    public void verify() throws NpStateMachineException {

    }

    @Override
    public boolean isSatisfied(Input input, Ctx ctx) {
        return condition.isSatisfied(input,ctx);
    }
}
