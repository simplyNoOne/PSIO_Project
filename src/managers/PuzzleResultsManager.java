package managers;

import data.Texture;
import interfaces.Interactible;
import main.ManagerHandler;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleResultsManager {

    public static class OkButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.nextState();
        }
    }

    private static final OkButtonListener okButtonListener = new OkButtonListener();


    public void init()
    {
        ((Interactible) ManagerHandler.getGUIManager().getPanel("puzzleResults")).addButtonListener(okButtonListener, "ok");
    }

}
