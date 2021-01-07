package cn.np.boots.statemachine;

import cn.np.boots.statemachine.api.NpStateMachineStructureBuilder;
import lombok.Data;

import java.util.Objects;

public class Test {
    static enum States {
        STATE1, STATE2, STATE3, STATE4
    }
    @Data
    static class InputEvent {
        private String event;
        private String input;

        public InputEvent(String event){
            this.event = event;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InputEvent that = (InputEvent) o;
            return event.equals(that.event);
        }

        @Override
        public int hashCode() {
            return Objects.hash(event);
        }
    }

    static class Context {
        String operator = "frank";
        String entityId = "123465";
    }

    static class TrueStateMachineCondition implements NpStateMachineCondition<InputEvent,Context> {
        @Override
        public boolean isSatisfied(InputEvent events, Context context) {
            if(events.input == "1") {
                return true;
            }
            else return false;
        }
    }

    public static void main(String[] args) {
        TrueStateMachineCondition alwaysTrue = new TrueStateMachineCondition();
        Context context = new Context();

        InputEvent inputEvent = new InputEvent("Test");
        String event = "trigger-event";


        NpStateMachineBuilder<States,String, InputEvent, Context> builder = new NpStateMachineStructureBuilder();

        builder.transition().from(States.STATE1).to(States.STATE2).on(event).when(alwaysTrue).perform((from, to, evt,input, ctx) -> {
            System.out.println(from);
        });

        NpStateMachine<States,String, InputEvent, Context> stateMachine = builder.build("test");

        InputEvent inputEvent2 = new InputEvent("Test");
        inputEvent2.setInput("1");

        stateMachine.fire(States.STATE1,event,inputEvent2,context);
    }
}
