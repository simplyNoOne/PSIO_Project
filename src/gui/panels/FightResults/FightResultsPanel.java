package gui.panels.FightResults;

import gui.panels.CustomPanel;
import managers.FightResultsManager;
import managers.GUIManager;

import java.awt.*;
import java.util.ArrayList;

public class FightResultsPanel extends CustomPanel
{
    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;
    public FightResultsPanel(){
        super();
        this.setBackground(Color.pink);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.add(FightResultsManager.getOkButton());

    }
}