package cn.np.boots.statemachine;

public interface NpStateMachineBuilder<State, Event, Ctx> {
    NpStateMachine<State, Event, Ctx> build(String machineId);
}
