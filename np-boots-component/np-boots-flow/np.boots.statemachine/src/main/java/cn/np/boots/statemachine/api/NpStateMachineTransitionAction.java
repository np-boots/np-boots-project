package cn.np.boots.statemachine.api;

import cn.np.boots.statemachine.NpStateMachineAction;
import cn.np.boots.statemachine.NpStateMachineCondition;

public class NpStateMachineTransitionAction<S,E,C> {
    public interface Init<S, E, C> {
        From<S, E, C> from(S stateId);
    }

    public interface From<S, E, C> {
        To<S, E, C> to(S stateId);
    }

    public interface To<S, E, C> {
        On<S, E, C> on(E event);
    }

    public interface On<S, E, C> extends When<S, E, C>{
        When<S, E, C> when(NpStateMachineCondition<E,C> condition);
    }

    public interface When<S, E, C>{
        void perform(NpStateMachineAction<S, E, C> action);
    }
}
