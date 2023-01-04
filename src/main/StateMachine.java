package main;

import data.Enemy;
import gui.panels.*;
import managers.*;

import javax.swing.*;

public class StateMachine {

    public enum State{
        START{
            public void initState(){}
            public void update(double deltaTime) {

                ResourceManager.loadResources();
                MainApp.spawnPlayer();

                MainApp.setEnemy(new Enemy());
                GUIManager.initAllPanels();
                nextState();

            }

            public void nextState() {
                setCurrentState(MENU);
                currentState.initState();
            }
        },
        GAME{
            public void initState(){
                MainApp.spawnPlayer();
                GUIManager.addPane("game");
                GUIManager.addPanel("background", "game");
                GUIManager.addPanel("playerStats", "game", 3);
                GUIManager.addPanel("characters", "game", 2);
                ((PlayerStatsPanel)GUIManager.getPanel("playerStats")).updateStats();
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

                GUIManager.addPane("menu");
                GUIManager.addPanel("menu", "menu");
                MainApp.getGameFrame().setVisible(true);
                MainApp.getGameFrame().repaint();

            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("menu", "menu");
                setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PUZZLE_OR_FIGHT{

            public void initState(){

                GUIManager.addPanel("puzzleOrFight", "game");
                GUIManager.addPanel("enemyStats", "game" );
                ((EnemyStatsPanel)GUIManager.getPanel("enemyStats")).updateStats();

                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("enemyStats", "game");
                GUIManager.removePanel("puzzleOrFight", "game");
                MainApp.getGameFrame().setVisible(true);
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
                GUIManager.addPanel("enemyStats", "game" );
                GUIManager.addPanel("prefight", "game");
                ((PrefightPanel)GUIManager.getPanel("prefight")).refreshButtons();
            }
            public void update(double deltaTime) {}

            public void nextState() {
                GUIManager.removePanel("prefight", "game");
                MainApp.getGameFrame().revalidate();
                MainApp.getGameFrame().repaint();
                StateMachine.setCurrentState(FIGHT);
                currentState.initState();
            }
        },
        FIGHT{
            public void initState(){
                GUIManager.addPanel("fight", "game");
                MainApp.getGameFrame().revalidate();
                MainApp.getGameFrame().repaint();

                System.out.println("done");
            }
            public void update(double deltaTime) {
                FightManager.attemptToFightRound(deltaTime);
            }

            public void nextState() {
                GUIManager.removePanel("fight", "game");

                StateMachine.setCurrentState(FIGHT_RESULTS);
                currentState.initState();
            }
        },
        FIGHT_RESULTS{
            public void initState(){
                GUIManager.removePanel("enemyStats", "game");
                GUIManager.addPanel("fightResults", "game");
                MainApp.getGameFrame().setVisible(true);
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
                ((CharactersPanel)GUIManager.getPanel("characters")).updatePrevEnemy();
                //here a new enemy will be generated, using a generator, ofc
                MainApp.setEnemy(new Enemy());
                ((CharactersPanel)GUIManager.getPanel("characters")).updateEnemyTexture();
                MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {
                GUIManager.getPanel("background").repaint();
                GUIManager.getPanel("characters").repaint();
                ((BackgroundPanel)GUIManager.getPanel("background")).scroll(deltaTime);
                ((CharactersPanel)GUIManager.getPanel("characters")).moveEnemies(deltaTime);
            }

            public void nextState() {

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
                GUIManager.removePanel("background", "game");
                GUIManager.removePanel("finalResults", "game");
                GUIManager.removePanel("playerStats", "game");
                GUIManager.removePanel("characters", "game");
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
        public abstract void update(double deltaTime);

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
