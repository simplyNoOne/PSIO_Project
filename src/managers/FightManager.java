package managers;

import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FightManager {

    public static class WonFightButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            StateMachine.nextState();
        }
    }

    public static class LostFightButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);
            StateMachine.nextState();
        }
    }

    public static class WonAndLevelupFightButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            StateMachine.nextState();
        }
    }

    private static final WonFightButtonListener wonFightButtonListener = new WonFightButtonListener();
    private static final LostFightButtonListener lostFightButtonListener = new LostFightButtonListener();

    private static final WonAndLevelupFightButtonListener wonAndLevelupFightButtonListener = new WonAndLevelupFightButtonListener();


    public static void init() {
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(wonFightButtonListener, "won");
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(lostFightButtonListener, "lost");
        ((Interactible)GUIManager.getPanel("fight")).addButtonListener(wonAndLevelupFightButtonListener, "levelup");
    }


}

