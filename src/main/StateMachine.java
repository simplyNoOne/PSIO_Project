package main;

import managers.GUIManager;
import gui.panels.BackgroundPanel;
import managers.MenuManager;
import managers.ResourceManager;

import javax.swing.*;

public class StateMachine {

    public enum State{
        START{
            public void initState(){}
            public void update(double deltaTime) {

                ResourceManager.loadResources();
                MainApp.spawnPlayer();
                GUIManager.initAllPanels();
                nextState();
                MainApp.loadAllResources();
                System.out.println("in start");

            }

            public void nextState() {
                setCurrentState(MENU);
                currentState.initState();
            }
        },
        GAME{
            public void initState(){
                GUIManager.addPane("game");
                GUIManager.addPanel("playerStats", "game", 3);
                MainApp.getGameFrame().setVisible(true);
                nextState();
            }
              public void update(double deltaTime) {System.out.println("in game");
              }

              public void nextState() {

                  setCurrentState(SCROLL_BG);
                  currentState.initState();
              }


          },    //probably should be also in here, but I have no idea what is it doing.
        MENU{
            public void initState(){
                MainApp.getGameFrame().repaint();
                GUIManager.addPane("menu");
                GUIManager.addPanel("menu", "menu");
                MainApp.getGameFrame().setVisible(true);
                MenuManager.init();
            }
            public void update(double deltaTime) {System.out.println("in meun");
            }

            public void nextState() {

                GUIManager.removePane("menu");
                setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PUZZLE_OR_FIGHT{

            public void initState(){
                GUIManager.addPanel("puzzleOrFight", "game");
                GUIManager.addPanel("enemyStats", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("puzzleOrFight", "game");
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PUZZLE{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(PUZZLE_RESULTS);
            }
        },
        PUZZLE_RESULTS{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        PREFIGHT{
            public void initState(){
                GUIManager.addPanel("prefight", "game");
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("prefight", "game");
                StateMachine.setCurrentState(FIGHT);
                currentState.initState();
            }
        },
        FIGHT{
            public void initState(){
                GUIManager.addPanel("fight", "game");
            }
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(FIGHT_RESULTS);
            }
        },
        FIGHT_RESULTS{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(nextStateVar);
            }
        },
        LEVELUP{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(SCROLL_BG);
            }
        },

        SCROLL_BG{
            public void initState(){
                GUIManager.addPanel("background", "game");
                panel = GUIManager.getPanel("background");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {
                panel.repaint();
                ((BackgroundPanel)panel).scroll(deltaTime);
            }

            public void nextState() {
                GUIManager.removePanel("background", "game");

                StateMachine.setCurrentState(PUZZLE_OR_FIGHT);
                currentState.initState();
            }
        },

        FINAL_RESULTS{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {
                StateMachine.setCurrentState(MENU);
            }
        },
        END{
            public void initState(){}
            public void update(double deltaTime) {}

            public void nextState() {}
        };

        public abstract void nextState();
        public abstract void update(double delteTime);

        public abstract void initState();
    }

    //private static State currentState = State.PUZZLE_OR_FIGHT;
    private static State currentState = State.START;
    private static State nextStateVar;
    static JPanel panel;

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
