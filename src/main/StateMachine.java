package main;
import gui.panels.*;
import managers.*;

import javax.swing.*;

public class StateMachine {

    public enum State{
        START{
            public void initState(){}
            public void update(double deltaTime) {

                ManagerHandler.getResourceManager().loadResources();


                MainApp.spawnPlayer();
                MainApp.setEnemy(ManagerHandler.getEnemyCreator().createEnemy());
                ManagerHandler.getGUIManager().initAllPanels();
                nextState();

                System.out.println("in start");

            }

            public void nextState() {
                setCurrentState(MENU);
                currentState.initState();
            }
        },
        GAME{
            public void initState(){
                //MainApp.spawnPlayer(); // in current structure, player must be spawned in the menu (earlier), because MenuManager sets player.name to Menu text field string
                ManagerHandler.getGUIManager().addPane("game");
                ManagerHandler.getGUIManager().addPanel("background", "game");
                ManagerHandler.getGUIManager().addPanel("playerStats", "game", 3);
                ManagerHandler.getGUIManager().addPanel("characters", "game", 2);
                ((PlayerStatsPanel)ManagerHandler.getGUIManager().getPanel("playerStats")).updateStats();
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
                MainApp.spawnPlayer(); // player spawns in Menu instead of in Game as a current fix of player.name reset due to default Player() construction in Game

                ManagerHandler.getGUIManager().addPane("menu");
                ManagerHandler.getGUIManager().addPanel("menu", "menu");
                MainApp.getGameFrame().setVisible(true);
                MainApp.getGameFrame().repaint();

            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("menu", "menu");
                setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PUZZLE_OR_FIGHT{

            public void initState(){

                ManagerHandler.getGUIManager().addPanel("puzzleOrFight", "game");
                ManagerHandler.getGUIManager().addPanel("enemyStats", "game" );
                ((EnemyStatsPanel)ManagerHandler.getGUIManager().getPanel("enemyStats")).updateStats();

               // MainApp.getGameFrame().setVisible(true);
            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("enemyStats", "game");
                ManagerHandler.getGUIManager().removePanel("puzzleOrFight", "game");
                MainApp.getGameFrame().setVisible(true);
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PUZZLE{
            float endingDelayTimeLeft = 3;
            public void initState(){
                endingDelayTimeLeft = 3;
                MainApp.getGameFrame().setVisible(true);
                ManagerHandler.getPuzzleManager().newPuzzle();
            }
            public void update(double deltaTime) {
                if(ManagerHandler.getPuzzleManager().isColorGameFinished() && ManagerHandler.getPuzzleManager().getPuzzleType().equals("colorgame")) {
                    endingDelayTimeLeft -= deltaTime;
                    if (endingDelayTimeLeft <= 0)
                    {
                        StateMachine.nextState();
                        ManagerHandler.getPuzzleManager().setColorGameFinished(false);
                    }
                }
            }

            public void nextState() {

                MainApp.getPlayer().modifyScore(ManagerHandler.getPuzzleManager().getScoreModifier());
                System.out.println(MainApp.getPlayer().getScore());
                ManagerHandler.getGUIManager().removePanel(ManagerHandler.getPuzzleManager().getPuzzleType(), "game");
                StateMachine.setCurrentState(PUZZLE_RESULTS);
                currentState.initState();
            }
        },
        PUZZLE_RESULTS{
            public void initState(){
                ((PuzzleResultsPanel) ManagerHandler.getGUIManager().getPanel("puzzleResults")).updateMessage();
                ManagerHandler.getGUIManager().addPanel("puzzleResults", "game");
                MainApp.getGameFrame().setVisible(true);
                MainApp.getGameFrame().repaint();
            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("puzzleResults", "game");
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        PREFIGHT{
            public void initState(){
                ManagerHandler.getGUIManager().addPanel("enemyStats", "game" );
                ManagerHandler.getGUIManager().addPanel("prefight", "game");
                ((EnemyStatsPanel)ManagerHandler.getGUIManager().getPanel("enemyStats")).updateStats();
                ((PrefightPanel)ManagerHandler.getGUIManager().getPanel("prefight")).refreshButtons();
                MainApp.getGameFrame().revalidate();
                MainApp.getGameFrame().repaint();
            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("prefight", "game");
                MainApp.getGameFrame().revalidate();
                MainApp.getGameFrame().repaint();
                StateMachine.setCurrentState(FIGHT);
                currentState.initState();
            }
        },
        FIGHT{
            public void initState(){
                ManagerHandler.getGUIManager().addPanel("fight", "game");
                MainApp.getGameFrame().revalidate();
                MainApp.getGameFrame().repaint();

                System.out.println("done");
            }
            public void update(double deltaTime) { ManagerHandler.getFightManager().attemptToFightRound(deltaTime);}

            public void nextState() {
                MainApp.getPlayer().modifyScore(ManagerHandler.getFightManager().getScoreModifier());
                System.out.println(MainApp.getPlayer().getScore());
                ManagerHandler.getGUIManager().removePanel("fight", "game");
                StateMachine.setCurrentState(FIGHT_RESULTS);
                currentState.initState();
            }
        },
        FIGHT_RESULTS{
            public void initState(){
                MainApp.getGameFrame().revalidate();
                ManagerHandler.getFightResultsManager().prepResults();
                ManagerHandler.getGUIManager().removePanel("enemyStats", "game");
                ManagerHandler.getGUIManager().addPanel("fightResults", "game");
                MainApp.getGameFrame().repaint();

            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("fightResults", "game");
                StateMachine.setCurrentState(nextStateVar);
                currentState.initState();
            }
        },
        LEVELUP{
            public void initState(){
                ManagerHandler.getGUIManager().addPanel("levelup", "game");
                ManagerHandler.getLevelUpManager().generalLevelUp();
            }
            public void update(double deltaTime) {
                MainApp.getGameFrame().repaint();
            }

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("levelup", "game");
                StateMachine.setCurrentState(SCROLL_BG);
                currentState.initState();
            }
        },

        SCROLL_BG{
            public void initState(){
                ((CharactersPanel)ManagerHandler.getGUIManager().getPanel("characters")).updatePrevEnemy();
                MainApp.setEnemy(ManagerHandler.getEnemyCreator().createEnemy());
                ((CharactersPanel)ManagerHandler.getGUIManager().getPanel("characters")).updateEnemyTexture();
            }
            public void update(double deltaTime) {

                ((BackgroundPanel)ManagerHandler.getGUIManager().getPanel("background")).scroll(deltaTime);
                ((CharactersPanel)ManagerHandler.getGUIManager().getPanel("characters")).moveCharacters(deltaTime);
                ManagerHandler.getGUIManager().getPanel("background").repaint();
                ManagerHandler.getGUIManager().getPanel("characters").repaint();
            }

            public void nextState() {
                if(MainApp.getEnemy().getIsBoss())
                    StateMachine.setCurrentState(PREFIGHT);
                else
                    StateMachine.setCurrentState(PUZZLE_OR_FIGHT);
                currentState.initState();
            }
        },

        FINAL_RESULTS{
            public void initState(){
                ((FinalResultsPanel)ManagerHandler.getGUIManager().getPanel("finalResults")).updateMessage();
                ManagerHandler.getGUIManager().addPanel("finalResults", "game");
                ManagerHandler.getScoreManager().updateEntryInFile(MainApp.getPlayer().getName(),MainApp.getPlayer().getScore());
            }
            public void update(double deltaTime) {}

            public void nextState() {
                ManagerHandler.getGUIManager().removePanel("background", "game");
                ManagerHandler.getGUIManager().removePanel("finalResults", "game");
                ManagerHandler.getGUIManager().removePanel("playerStats", "game");
                ManagerHandler.getGUIManager().removePanel("characters", "game");
                ((CharactersPanel)ManagerHandler.getGUIManager().getPanel("characters")).dispatchEnemy();
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
