package managers;

import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleManager {

    public static class CorrectSolutionButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            StateMachine.nextState();
        }
    }

    public static class WrongSolutionButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
            StateMachine.nextState();
        }
    }

    private static final CorrectSolutionButtonListener correctSolutionButtonListener = new CorrectSolutionButtonListener();
    private static final WrongSolutionButtonListener wrongSolutionButtonListener = new WrongSolutionButtonListener();



    public static void init() {
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(correctSolutionButtonListener, "correct");
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(wrongSolutionButtonListener, "wrong");
    }

}
