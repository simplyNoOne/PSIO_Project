package managers;

import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuManager {

    static class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextStateVar(StateMachine.State.GAME);
            StateMachine.nextState();
        }
    }
    static class QuittListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quitting game");
            StateMachine.setNextStateVar(StateMachine.State.END);
            StateMachine.nextState();
        }
    }
    static class ScoresListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            GUIManager.addPanel("scores", "menu");
            GUIManager.getPane("menu").repaint();
        }
    }
    static class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("BAck");
            GUIManager.removePanel("scores", "menu");
            GUIManager.getPane("menu").repaint();
        }
    }

    private final static StartListener startListener = new StartListener();
    private final static QuittListener quittListener = new QuittListener();
    private final static ScoresListener scoresListener = new ScoresListener();
    private final static BackListener backListener = new BackListener();

    public static void init(){
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(startListener, "start");
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(quittListener, "quit");
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(scoresListener, "scores");
        ((Interactible)GUIManager.getPanel("scores")).addButtonListener(backListener, "");

    }





}
