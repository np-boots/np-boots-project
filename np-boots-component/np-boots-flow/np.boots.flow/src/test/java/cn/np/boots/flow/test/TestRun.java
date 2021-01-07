package cn.np.boots.flow.test;

import cn.np.boots.flow.NpFlowEnginePlugin;
import cn.np.boots.flow.NpFlowInput;
import cn.np.boots.flow.NpFlowOutput;
import cn.np.boots.flow.test.base.IncomingFlowEngine;
import cn.np.boots.flow.test.base.IncomingFlowInput;
import cn.np.boots.flow.test.base.IncomingFlowNode;
import cn.np.boots.flow.test.base.IncomingFlowNodeStart;
import cn.np.boots.flow.test.ivr.base.*;

import java.util.ArrayList;
import java.util.List;

public class TestRun {

    static class FlowEngineLogPlugin implements NpFlowEnginePlugin<String, IvrFlowInput, IvrFlowOutput, String, IvrFlowContext> {
        @Override
        public void onExecuting(NpFlowInput<String, IvrFlowInput, String, IvrFlowContext> input, String incomingFlowNode) {
            System.out.println("日志记录-流程触发节点执行:" + incomingFlowNode);
        }

        @Override
        public void onExecuted(NpFlowInput<String, IvrFlowInput, String, IvrFlowContext> input, NpFlowOutput<String, IvrFlowOutput> output) {
            System.out.println("日志记录-流程节点执行完毕:" + output.getNode()+" 结果"+output.getData());
        }
    }

    public static void main(String[] args) {
        IncomingFlowEngine flowEngine = new IncomingFlowEngine();

        flowEngine.plugin(new FlowEngineLogPlugin());

        List<IncomingFlowNode> nodes = getNodes();
        nodes.forEach(node -> {
            flowEngine.node(node);
        });
        flowEngine.start();

        IvrFlowContext context = new IvrFlowContext();


        String requestTargetFlowId = "START";
        String input = "1";

        IvrFlowInput flowInput = new IvrFlowInput();
        flowInput.setInput(input);
        IncomingFlowInput<IvrFlowInput, IvrFlowContext> request = new IncomingFlowInput<IvrFlowInput, IvrFlowContext>(requestTargetFlowId, flowInput, context);

        NpFlowOutput<String, IvrFlowOutput> result = flowEngine.execute(request);
        System.out.println(result);
    }

    private static List<IncomingFlowNode> getNodes() {

        List<IncomingFlowNode> nodes = new ArrayList<>();

        IvrFlowNodeManual manual1 = new IvrFlowNodeManual("manual1", "技能组1");
        IvrFlowNodeManual manual2 = new IvrFlowNodeManual("manual2", "技能组2");
        IvrFlowNodeManual manual3 = new IvrFlowNodeManual("manual3", "技能组3");

        IvrFlowNodeMenu menu = new IvrFlowNodeMenu("main");
        menu.add("技能组A咨询", "1", manual1.getFlowId());
        menu.add("技能组B咨询", "2", manual2.getFlowId());
        menu.add("技能组C咨询", "3", manual3.getFlowId());

        IncomingFlowNodeStart start = new IncomingFlowNodeStart(menu.getFlowId().toString());

        nodes.add(manual1);
        nodes.add(manual3);
        nodes.add(manual2);
        nodes.add(menu);
        nodes.add(start);
        return nodes;
    }
}
