package cn.np.boots.statemachine;

public class Test {
    static enum States {
        STATE1, STATE2, STATE3, STATE4
    }

    static enum Events {
        EVENT1, EVENT2, EVENT3, EVENT4, INTERNAL_EVENT
    }

    static class Context {
        String operator = "frank";
        String entityId = "123465";
    }

    static class TrueStateMachineCondition implements StateMachineCondition<Context> {
        @Override
        public boolean isSatisfied(Context context) {
            return true;
        }

        @Override
        public String name() {
            return "true";
        }
    }

    public static void main(String[] args) {
        TrueStateMachineCondition alwaysTrue = new TrueStateMachineCondition();
        Context context = new Context();

        StateMachineStructureBuilder<States, Events, Context> builder = StateMachineBuilderFactory.structure();

        builder.transition().from(States.STATE1).to(States.STATE2).on(Events.EVENT1).when(alwaysTrue).perform((from, to, event, ctx) -> {
            System.out.println(from);
        });

        StateMachine<States, Events, Context> stateMachine = builder.build("test");

        stateMachine.fire(States.STATE1,Events.EVENT1,context);
    }
}
