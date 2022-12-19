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
                GUIManager.removePane("menu");
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
            public void initState(){
                GUIManager.addPanel("puzzle", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("puzzle", "game");
                StateMachine.setCurrentState(PUZZLE_RESULTS);
                currentState.initState();
            }
        },
        PUZZLE_RESULTS{
            public void initState(){
                GUIManager.addPanel("puzzleResults", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("puzzleResults", "game");
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PREFIGHT{
            public void initState(){
                GUIManager.addPanel("prefight", "game");
                MainApp.getGameFrame().setVisible(true);
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
                MainApp.getGameFrame().setVisible(true); //TODO check if this line is important
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("fight", "menu");
                StateMachine.setCurrentState(FIGHT_RESULTS);
                currentState.initState();
            }
        },
        FIGHT_RESULTS{
            public void initState(){
                GUIManager.addPanel("fightResults", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("fightResults", "game");
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        LEVELUP{
            public void initState(){
                GUIManager.addPanel("levelup", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("levelup", "game");
                StateMachine.setCurrentState(SCROLL_BG);
                currentState.initState();
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
            public void initState(){
                GUIManager.addPanel("finalResults", "game");
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("finalResults", "game");
                StateMachine.setCurrentState(MENU);
                currentState.initState();
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

    public static void setNextStateVar(State state){
        nextStateVar = state;
    }

    public static void update(double deltaTime){
        currentState.update(deltaTime);
    }


}
