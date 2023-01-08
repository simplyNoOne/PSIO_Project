package managers;

import gui.panels.*;
import gui.panels.PrefightPanel;
import gui.panels.QuizPanel;
import gui.panels.PuzzleOrFightPanel;
import gui.panes.CustomLayerPane;
import gui.panes.GamePane;
import gui.panes.MenuPane;
import main.MainApp;
import main.ManagerHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int POS_X = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - WIDTH)/2;
    private static final int POS_Y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - HEIGHT)/2;

    private Map<String, CustomPanel>  panels = new HashMap<>();
    private Map<String, CustomLayerPane>  panes = new HashMap<>();


    public JPanel getPanel(String id){
        return panels.get(id);
    }
    public JPanel getPane(String id){
        return panes.get(id);
    }

    public static int getWidth(){return WIDTH;}
    public static int getHeight(){return HEIGHT;}
    public static int getPosX(){return POS_X;}
    public static int getPosY(){return POS_Y;}

    public void initAllPanels() {
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
        panels.put("quiz", new QuizPanel());
        panels.put("colorgame", new ColorGamePanel());
        panels.put("fightResults", new FightResultsPanel());
        panels.put("puzzleResults", new PuzzleResultsPanel());
        panels.put("finalResults", new FinalResultsPanel());
        panels.put("levelup", new LevelUpPanel());
        panels.put("characters", new CharactersPanel());

        ManagerHandler.getMenuManager().init();
        ManagerHandler.getPuzzleOrFightManager().init();
        ManagerHandler.getPuzzleManager().init();
        ManagerHandler.getPuzzleResultsManager().init();
        ManagerHandler.getPrefightManager().init();
        ManagerHandler.getFightManager().init();
        ManagerHandler.getFightResultsManager().init();
        ManagerHandler.getFinalResultsManager().init();
        ManagerHandler.getLevelUpManager().init();
    }



    public void addPane(String id){
        MainApp.getGameFrame().setContentPane(panes.get(id));
    }


    public void addPanel(String id, String targetId){
        panes.get(targetId).addLayer(panels.get(id));
        panels.get(id).setVisible(true);
    }

    public void addPanel(String id, String targetId, int layer){
        panes.get(targetId).addLayer(panels.get(id), layer);
        panels.get(id).setVisible(true);
    }
    public void removePanel(String id, String targetId){

        panes.get(targetId).removeLayer(panels.get(id));
    }

}
