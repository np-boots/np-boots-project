package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;

public class NpStateMachineTransitionAction<S, E, Input, C> {
    public interface Init<S, E, I, C> {
        From<S, E, I, C> from(S stateId);
    }

    public interface From<S, E, I, C> {
        To<S, E, I, C> to(S stateId);
    }

    public interface To<S, E, I, C> {
        On<S, E, I, C> on(E event);
    }

    public interface On<S, E, I, C> extends When<S, E, I, C> {
        When<S, E, I, C> when(NpStateMachineCondition<I, C> condition);
    }

    public interface When<S, E, I, C> {
        void perform(NpStateMachineAction<S, E, I, C> action);
    }
}
