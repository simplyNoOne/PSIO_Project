package gui.panels.PuzzleOrFight;

import MVC.StateMachine;
import controller.Managers.ResourceManager;
import controller.Managers.Texture;
import gui.GUIManager;
import gui.panels.Fight.buttons.CarryOutTheFightButton;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleOrFightManager {
    private static final int TITLE_BAR_HEIGHT = 31;
    private static final int HORIZONTAL_BUTTON_SPACING = 50;

    private static final ChoiceButtonListener choiceButtonListener = new ChoiceButtonListener();

    public static class ChoiceButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected_option = ((ChoiceButton) e.getSource()).getChoiceValue();
            if(selected_option.equals("fight")) StateMachine.setCurrentState(StateMachine.State.PREFIGHT);
            else StateMachine.setCurrentState(StateMachine.State.PUZZLE);
        }
    }

    public static ArrayList<ChoiceButton> getChoiceButtons(){
        ArrayList<ChoiceButton> choiceButtons = new ArrayList<>();

        Texture fightTexture = new Texture(ResourceManager.getTexture("fight").getTexturePath());
        int x0f = (GUIManager.getWidth() - HORIZONTAL_BUTTON_SPACING)/2 - fightTexture.getIconWidth();
        int y0f = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - fightTexture.getIconHeight())/2;
        ChoiceButton fightButton = new ChoiceButton("fight", fightTexture);
        fightButton.setBounds(x0f, y0f, fightTexture.getIconWidth(), fightTexture.getIconHeight());
        fightButton.addActionListener(choiceButtonListener);
        choiceButtons.add(fightButton);

        Texture puzzleTexture = new Texture(ResourceManager.getTexture("puzzle").getTexturePath());
        int x0p = (GUIManager.getWidth() + HORIZONTAL_BUTTON_SPACING)/2;
        int y0p = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - puzzleTexture.getIconHeight())/2;
        ChoiceButton puzzleButton = new ChoiceButton("puzzle", puzzleTexture);
        puzzleButton.setBounds(x0p, y0p, puzzleTexture.getIconWidth(), puzzleTexture.getIconHeight());
        puzzleButton.addActionListener(choiceButtonListener);
        choiceButtons.add(puzzleButton);



        return choiceButtons;
    }

}