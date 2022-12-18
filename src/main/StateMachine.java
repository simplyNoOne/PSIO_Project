package main;

import managers.GUIManager;
import gui.panels.BackgroundPanel;
import managers.MenuManager;
import managers.ResourceManager;

public class StateMachine {

    public enum State{
        START{
            public void update(double deltaTime) {
                ResourceManager.loadResources();
                GUIManager.initAllPanels();
                nextState();

            }

            public void nextState() {
                GUIManager.addPane("menu");
                GUIManager.addPanel("menu", "menu");
                MainApp.getGameFrame().setVisible(true);
                MenuManager.init();
                StateMachine.setCurrentState(MENU);
            }
        },
        GAME{
              public void update(double deltaTime) {
                  nextState();
              }

              public void nextState() {
                  GUIManager.addPanel("background", "game");
                  MainApp.getGameFrame().setVisible(true);
                  StateMachine.setCurrentState(SCROLL_BG);
              }


          },    //probably should be also in here, but I have no idea what is it doing.
        MENU{
            public void update(double deltaTime) {
                GUIManager.getPanel("menu").repaint();
            }

            public void nextState() {
                if (nextStateVar == GAME) {
                    GUIManager.removePane("menu");
                    GUIManager.addPane("game");
                    MainApp.getGameFrame().setVisible(true);
                }
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        PUZZLE_OR_FIGHT{
            public void update(double deltaTime) {
                GUIManager.getPanel("puzzleOrFight").repaint();
            }

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
            public void update(double deltaTime) {
                GUIManager.getPanel("background").repaint();
                ((BackgroundPanel)GUIManager.getPanel("background")).scroll(deltaTime);
            }

            public void nextState() {
                GUIManager.removePanel("background", "game");
                GUIManager.addPanel("puzzleOrFight", "game");
                MainApp.getGameFrame().setVisible(true);
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

            public void nextState() {}
        };

        public abstract void nextState();
        public abstract void update(double delteTime);
    }

    //private static State currentState = State.PUZZLE_OR_FIGHT;
    private static State currentState = State.START;

    private static State nextStateVar;


    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State newState) {
        currentState = newState;
    }

    public static void nextState()
    {

        //System.out.println(currentState);
        currentState.nextState();
        System.out.println(currentState);
    }

    public static void setNextState(State state){
        nextStateVar = state;
    }

    public static void update(double deltaTime){
        currentState.update(deltaTime);
    }

}
