package cn.np.boots.flow.test.ivr.base;

import lombok.Data;

@Data
public class IvrFlowOutput {
    private String action;

    public IvrFlowOutput(String action) {
        this.action = action;
    }
}
