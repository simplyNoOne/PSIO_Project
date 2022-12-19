package gui.panels.Puzzle;

import gui.panels.CustomPanel;
import gui.panels.Puzzle.buttons.OkButton;
import managers.GUIManager;
import managers.PuzzleManager;

import java.awt.*;

public class PuzzlePanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;
    public PuzzlePanel(){
        super();
        this.setBackground(Color.black);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(PuzzleManager.getOkButton());

    }

}
