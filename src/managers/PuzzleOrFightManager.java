package managers;

import gui.panels.PuzzleOrFight.PuzzleOrFightPanel;
import main.StateMachine;
import data.Texture;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleOrFightManager {

    private static final PuzzleButtonListener puzzleButtonListener = new PuzzleButtonListener();
    private static final FightButtonListener fightButtonListener = new FightButtonListener();

    public static class PuzzleButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.PUZZLE);
            StateMachine.nextState();
        }
    }

    public static class FightButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
            StateMachine.nextState();
        }
    }



    public static void init() {
        ((PuzzleOrFightPanel)GUIManager.getPanel("puzzleOrFight")).addButtonListener(puzzleButtonListener, "puzzle");
        ((PuzzleOrFightPanel)GUIManager.getPanel("puzzleOrFight")).addButtonListener(fightButtonListener, "fight");
    }

}