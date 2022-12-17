package MVC;

import main.Main;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StateMachine {

    public enum State{
        START{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(MENU);
            }
        },
//TODO  GAME{...},    probably should be also in here, but I have no idea what is it doing.
        MENU{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        PUZZLE_OR_FIGHT{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        PUZZLE{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(PUZZLE_RESULTS);
            }
        },
        PUZZLE_RESULTS{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        PREFIGHT{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(FIGHT);
            }
        },
        FIGHT{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(FIGHT_RESULTS);
            }
        },
        FIGHT_RESULTS{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        LEVELUP{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(SCROLL_BG);
            }
        },

        SCROLL_BG{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(PUZZLE_OR_FIGHT);
            }
        },

        FINAL_RESULTS{
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(MENU);
            }
        },
        END{
            public void update(double deltaTime) {}

            public void nextState() {
                //???
            }
        };

        public abstract void nextState();
        public abstract void update(double delteTime);
    }

    private static State currentState = State.PUZZLE_OR_FIGHT;

    private static State nextStateVar;


    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State newState) {
        currentState = newState;
        System.out.println(currentState);
    }

    public static void nextState()
    {
        System.out.println(currentState);
        currentState.nextState();
        System.out.println(currentState);
    }

}
