package gui.panels.PuzzleOrFight;

import gui.panels.CustomPanel;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;
import managers.PuzzleOrFightManager;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleOrFightPanel extends CustomPanel {

    public PuzzleOrFightPanel(){
        super();
        ArrayList<ChoiceButton> choiceButtons = PuzzleOrFightManager.getChoiceButtons();
        this.add(choiceButtons.get(0));
        this.add(choiceButtons.get(1));

    }

}
