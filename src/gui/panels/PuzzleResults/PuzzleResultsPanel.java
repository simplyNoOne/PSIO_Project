package gui.panels.PuzzleResults;

import gui.panels.CustomPanel;
import managers.PuzzleResultsManager;
import managers.GUIManager;

import java.awt.*;

public class PuzzleResultsPanel extends CustomPanel
{
    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;
    public PuzzleResultsPanel(){
        super();
        this.setBackground(Color.MAGENTA);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.add(PuzzleResultsManager.getOkButton());

    }
}