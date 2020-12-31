package cn.np.boots.statemachine;

import cn.np.boots.statemachine.api.expcetion.NpStateMachineException;

public interface NpStateMachineSelfVerify {
    void verify() throws NpStateMachineException;
}
