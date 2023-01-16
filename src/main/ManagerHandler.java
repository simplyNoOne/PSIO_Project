package main;

import generators.EnemyCreator;
import managers.*;

public class ManagerHandler {


        private static final GUIManager guiManager = new GUIManager();
        private static final FightManager fightManager = new FightManager();
        private static final FightResultsManager fightResultsManager= new FightResultsManager();
        private static final FinalResultsManager finalResultsManager = new FinalResultsManager();
        private static final LevelUpManager levelUpManager = new LevelUpManager() ;
        private static final MenuManager menuManager= new MenuManager();
        private static final PrefightManager prefightManager = new PrefightManager();
        private static final PuzzleManager puzzleManager = new PuzzleManager();
        private static final PuzzleOrFightManager puzzleOrFightManager = new PuzzleOrFightManager();
        private static final PuzzleResultsManager puzzleResultsManager = new PuzzleResultsManager();
        private static final ResourceManager resourceManager = new ResourceManager();
        private static final ScoreManager scoreManager = new ScoreManager();
        private static final EnemyCreator enemyCreator = new EnemyCreator();


        public static GUIManager getGUIManager() {
                return guiManager;
        }

        public static FightManager getFightManager() {
                return fightManager;
        }

        public static FightResultsManager getFightResultsManager() {
                return fightResultsManager;
        }

        public static FinalResultsManager getFinalResultsManager() {
                return finalResultsManager;
        }

        public static LevelUpManager getLevelUpManager() {
                return levelUpManager;
        }

        public static MenuManager getMenuManager() {
                return menuManager;
        }

        public static PrefightManager getPrefightManager() {
                return prefightManager;
        }

        public static PuzzleManager getPuzzleManager(){return puzzleManager;}
        public static PuzzleOrFightManager getPuzzleOrFightManager() {
                return puzzleOrFightManager;
        }

        public static PuzzleResultsManager getPuzzleResultsManager() {
                return puzzleResultsManager;
        }

        public static ResourceManager getResourceManager() {
                return resourceManager;
        }

        public static ScoreManager getScoreManager() {
                return scoreManager;
        }
        public static EnemyCreator getEnemyCreator(){return enemyCreator;}
}
