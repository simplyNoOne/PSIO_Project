package managers;

import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleOrFightManager {

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

    private final static PuzzleButtonListener puzzleButtonListener = new PuzzleButtonListener();
    private final static FightButtonListener fightButtonListener = new FightButtonListener();


    public static void init() {
        ((Interactible)GUIManager.getPanel("puzzleOrFight")).addButtonListener(puzzleButtonListener, "puzzle");
        ((Interactible)GUIManager.getPanel("puzzleOrFight")).addButtonListener(fightButtonListener, "fight");
    }

}