package gui.panels.FinalResults;

import gui.panels.CustomPanel;
import managers.FightResultsManager;
import managers.GUIManager;

import java.awt.*;

public class FinalResultsPanel extends CustomPanel {
    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;

    public FinalResultsPanel(){
        super();
        this.setBackground(Color.gray);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.add(FightResultsManager.getOkButton());

    }
}
