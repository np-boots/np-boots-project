package cn.np.boots.flow.standard.engine;

import cn.np.boots.common.pattern.NpPattern;
import cn.np.boots.flow.*;
import cn.np.boots.flow.exception.NpFlowRuntimeException;
import cn.np.boots.statemachine.NpStateMachine;
import cn.np.boots.statemachine.NpStateMachineBuilder;
import cn.np.boots.statemachine.api.NpStateMachineStructureBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpStateMachineFlowEngine<Node, In, Out, Event, Ctx>
        implements NpFlowEngine<Node, In, Out, Event, Ctx> {

    private NpStateMachineBuilder<Node, Event, In, Ctx> builder = new NpStateMachineStructureBuilder();
    private NpStateMachine<Node, Event, In, Ctx> stateMachine;
    private Map<Node, NpFlowNode<Node, In, Out, Event, Ctx>> nodes = new HashMap<>();
    private List<NpFlowEnginePlugin<Node, In, Out, Event, Ctx>> plugins = new ArrayList<>();
    private boolean isReady = false;

    @Override
    public void relation(NpFlowLine<Node, In, Event, Ctx> relation) {
        NpPattern.asserts().notNull(relation, "FlowEngine register failed: relation can not be null ");
        NpPattern.asserts().notNull(relation.getFrom(), "FlowEngine register failed : relation's from can not be null");
        NpPattern.asserts().notNull(relation.getTo(), "FlowEngine register failed : relation's to can not be null");
        NpPattern.asserts().notNull(relation.getEvent(), "FlowEngine register failed : relation's event can not be null");

        builder.transition()
                .from(relation.getFrom())
                .to(relation.getTo())
                .on(relation.getEvent())
                .when(relation::isSatisfied)
                .perform(relation::action);
    }

    @Override
    public void node(NpFlowNode<Node, In, Out, Event, Ctx> node) {
        if (!nodes.containsKey(node.getFlowId())) {
            nodes.put(node.getFlowId(), node);
        }
        this.relation(node.getLines());
    }

    @Override
    public void start() {
        stateMachine = builder.build("test");
        if (stateMachine == null) {
            throw new NpFlowRuntimeException("FlowEngine start failed ");
        }
        this.isReady = true;
    }

    @Override
    public void plugin(NpFlowEnginePlugin<Node, In, Out, Event, Ctx> plugin) {
        plugins.add(plugin);
    }

    @Override
    public NpFlowOutput<Node, Out> execute(NpFlowInput<Node, In, Event, Ctx> input) {
        NpPattern.asserts().notNull(input.getEvent(), "FlowEngine execute failed : event can not be null");
        NpPattern.asserts().notNull(input.getTargetFlowId(), "FlowEngine execute failed : targetFlowId can not be null");

        if (!isReady) {
            throw new NpFlowRuntimeException("FlowEngine execute failed : engine is not ready");
        }

        Node fireNode = stateMachine.fire(input.getTargetFlowId(), input.getEvent(), input.getInput(), input.getContext());
        NpPattern.asserts().notNull(fireNode, "FlowEngine-execute: fire node is null ");

        plugins.forEach(plugin -> {
            plugin.onExecuting(input, fireNode);
        });

        NpFlowNode<Node, In, Out, Event, Ctx> flowNode = this.nodes.get(fireNode);
        NpPattern.asserts().notNull(flowNode, "FlowEngine execute failed : fire node [{}] can not found: ", fireNode.toString());

        NpFlowOutput<Node, Out> result = flowNode.execute(input.getEvent(), input.getInput(), input.getContext());

        plugins.forEach(plugin -> {
            plugin.onExecuted(input, result);
        });

        return result;
    }
}
