package gui.panels.PuzzleOrFight;

import gui.panels.CustomPanel;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;
import managers.GUIManager;
import managers.PuzzleOrFightManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleOrFightPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;
    public PuzzleOrFightPanel(){
        super();
        this.setBackground(Color.orange);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        ArrayList<ChoiceButton> choiceButtons = PuzzleOrFightManager.getChoiceButtons();
        this.add(choiceButtons.get(0));
        this.add(choiceButtons.get(1));

    }

}
