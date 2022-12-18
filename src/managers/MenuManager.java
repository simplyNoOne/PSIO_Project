package managers;

import gui.panels.CustomPanel;
import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuManager {

    static class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.setNextState(StateMachine.State.GAME);
            StateMachine.nextState();
        }
    }
    static class QuittListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quitting game");
            StateMachine.setNextState(StateMachine.State.END);
            StateMachine.nextState();
        }
    }
    static class ScoresListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GUIManager.addPanel("scores", "menu");
        }
    }
    static class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("BAck");
            GUIManager.removePanel("scores", "menu");
           // GUIManager.getPanel("menu").repaint();
        }
    }

    static StartListener startListener = new StartListener();
    static QuittListener quittListener = new QuittListener();
    static ScoresListener scoresListener = new ScoresListener();
    static BackListener backListener = new BackListener();

    public static void init(){
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(startListener, "start");
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(quittListener, "quit");
        ((Interactible)GUIManager.getPanel("menu")).addButtonListener(scoresListener, "scores");
        ((Interactible)GUIManager.getPanel("scores")).addButtonListener(backListener, "");

    }





}
