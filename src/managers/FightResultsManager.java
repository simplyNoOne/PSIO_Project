package managers;

import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightResultsManager {

    public static class OkButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.nextState();
        }
    }

    private static final OkButtonListener okButtonListener = new OkButtonListener();


    public static void init()
    {
        ((Interactible)GUIManager.getPanel("fightResults")).addButtonListener(okButtonListener, "ok");
    }

}
