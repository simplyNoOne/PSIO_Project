package gui.panels.PuzzleOrFight;

import gui.GUIManager;
import gui.panels.CustomPanel;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.util.ArrayList;

public class PuzzleOrFightPanel extends CustomPanel {

    public PuzzleOrFightPanel(){
        super();
        ArrayList<ChoiceButton> choiceButtons = PuzzleOrFightManager.getChoiceButtons();
        this.add(choiceButtons.get(0));
        this.add(choiceButtons.get(1));

    }
}
