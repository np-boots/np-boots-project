package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;

public class NpStateMachineTransitionAction<S,T, E, Input, C> {
    public interface Init<S,T, E, I, C> {
        From<S,T, E, I, C> from(S stateId);
    }

    public interface From<S,T, E, I, C> {
        To<S,T, E, I, C> to(S stateId);
    }

    public interface To<S,T, E, I, C> {
        On<S,T, E, I, C> on(E event);
    }

    public interface On<S,T, E, I, C> extends When<S,T, E, I, C> {
        When<S,T, E, I, C> when(NpStateMachineCondition<I, C> condition);
    }

    public interface When<S,T, E, I, C> {
        void perform(NpStateMachineAction<S, E, I, C> action);
    }
}
