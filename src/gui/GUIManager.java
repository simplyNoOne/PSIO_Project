package gui;

import gui.panels.*;
import main.Main;
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


    public static int getWidth(){return WIDTH;}
    public static int getHeight(){return HEIGHT;}
    public static int getPosX(){return POS_X;}
    public static int getPosY(){return POS_Y;}

    public static void initAllPanels(){
        panels.put("menu", new MenuPanel());
        panels.put("game", new GamePanel());
        panels.put("nickname", new PromptNicknamePanel());
        panels.put("playerStats", new PlayerStatsPanel());
        panels.put("enemyStats", new EnemyStatsPanel());
        panels.put("scores", new ScoresPanel());
    }

    public static JPanel getPanel(String id){
        return panels.get(id);
    }

    public static void addPanel(String id){
        Main.getGameFrame().getContentPane().add(panels.get(id));
    }

    public static void removePanel(String id){
        Main.getGameFrame().getContentPane().remove(panels.get(id));
    }

    public static void addPanel(String id, String targetId){
        panels.get(targetId).add(panels.get(id));
    }

    public static void removePanel(String id, String targetId){
        panels.get(targetId).remove(panels.get(id));
    }

}
