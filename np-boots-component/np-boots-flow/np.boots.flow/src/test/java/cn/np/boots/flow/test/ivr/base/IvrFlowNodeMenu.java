package cn.np.boots.flow.test.ivr.base;

import cn.np.boots.flow.NpFlowOutput;
import cn.np.boots.flow.test.base.IncomingFlowNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class IvrFlowNodeMenu extends IncomingFlowNode<IvrFlowInput, IvrFlowOutput, IvrFlowContext> {
    private List<MenuItem> items = new ArrayList<>();

    public IvrFlowNodeMenu(String flowId) {
        super(flowId);
    }

    public IvrFlowNodeMenu add(String text, String value, String to) {
        this.items.add(new MenuItem(text, value, to));
        this.addRelation(new IvrFlowLineInputEq(value, this.getFlowId(), to));
        return this;
    }

    @Override
    public NpFlowOutput<String, IvrFlowOutput> execute(String s, IvrFlowInput ivrFlowInput, IvrFlowContext ivrFlowContext) {
        String menuText = "";
        for (MenuItem menuItem : items) {
            menuText += menuItem.text + "请按" + menuItem.value + ",";
        }
        return NpFlowOutput.success(this.getFlowId(), new IvrFlowOutput("播报:" + menuText));
    }

    @Data
    @AllArgsConstructor
    class MenuItem {
        private String text;
        private String value;
        private String to;
    }
}
