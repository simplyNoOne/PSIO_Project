package managers;

import gui.panels.*;
import gui.panels.PrefightPanel;
import gui.panels.PuzzlePanel;
import gui.panels.PuzzleOrFightPanel;
import gui.panes.CustomLayerPane;
import gui.panes.GamePane;
import gui.panes.MenuPane;
import main.MainApp;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int POS_X = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - WIDTH)/2;
    private static final int POS_Y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - HEIGHT)/2;

    private static Map<String, CustomPanel>  panels = new HashMap<>();
    private static Map<String, CustomLayerPane>  panes = new HashMap<>();


    public static JPanel getPanel(String id){
        return panels.get(id);
    }
    public static JPanel getPane(String id){
        return panes.get(id);
    }

    public static int getWidth(){return WIDTH;}
    public static int getHeight(){return HEIGHT;}
    public static int getPosX(){return POS_X;}
    public static int getPosY(){return POS_Y;}

    public static void initAllPanels() {
        panes.put("menu", new MenuPane());
        panes.put("game", new GamePane());

        panels.put("menu", new MenuPanel());
        panels.put("playerStats", new PlayerStatsPanel());
        panels.put("enemyStats", new EnemyStatsPanel());
        panels.put("scores", new ScoresPanel());

        panels.put("background", new BackgroundPanel());
        panels.put("puzzleOrFight", new PuzzleOrFightPanel());
        panels.put("prefight", new PrefightPanel());
        panels.put("fight", new FightPanel());
        panels.put("puzzle", new PuzzlePanel());
        panels.put("fightResults", new FightResultsPanel());
        panels.put("puzzleResults", new PuzzleResultsPanel());
        panels.put("finalResults", new FinalResultsPanel());
        panels.put("levelup", new LevelUpPanel());
        panels.put("characters", new CharactersPanel());

        MenuManager.init();
        PuzzleOrFightManager.init();
        PuzzleManager.init();
        PuzzleResultsManager.init();
        PrefightManager.init();
        FightManager.init();
        FightResultsManager.init();
        FinalResultsManager.init();
        LevelUpManager.init();
    }



    public static void addPane(String id){
        MainApp.getGameFrame().setContentPane(panes.get(id));
    }


    public static void addPanel(String id, String targetId){
        panes.get(targetId).addLayer(panels.get(id));
        panels.get(id).setVisible(true);
    }

    public static void addPanel(String id, String targetId, int layer){
        panes.get(targetId).addLayer(panels.get(id), layer);
        panels.get(id).setVisible(true);
    }
    public static void removePanel(String id, String targetId){

        panes.get(targetId).removeLayer(panels.get(id));
    }

}
