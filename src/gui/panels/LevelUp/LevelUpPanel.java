package gui.panels.LevelUp;

import gui.panels.CustomPanel;
import gui.panels.LevelUp.buttons.UpgradeStatsButton;
import managers.LevelUpManager;
import managers.GUIManager;

import java.awt.*;

public class LevelUpPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;


    public LevelUpPanel(){
        super();
        this.setBackground(Color.magenta);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        for (UpgradeStatsButton button : LevelUpManager.getUpgradeStatsButtons()) {
            this.add(button);
        }

    }
}
