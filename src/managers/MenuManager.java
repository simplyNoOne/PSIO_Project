package managers;

import gui.panels.MenuPanel;
import gui.panels.ScoresPanel;
import interfaces.Interactible;
import main.MainApp;
import main.StateMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuManager {

    static class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setPlayerNick(); // loads nickname from text field after clicking start button

            StateMachine.setNextStateVar(StateMachine.State.GAME);
            StateMachine.nextState();
        }

        private void setPlayerNick() {
            String nickname = ((MenuPanel) GUIManager.getPanel("menu")).getNickField().getText();
            MainApp.getPlayer().setName(nickname);
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

            ((MenuPanel)GUIManager.getPanel("menu")).showButtons(false);

            fillScoresPanel();

            GUIManager.addPanel("scores", "menu");
            GUIManager.getPane("menu").repaint();
        }

        private void fillScoresPanel() {
            ScoreManager.loadEntries();
            ((ScoresPanel) GUIManager.getPanel("scores")).setScoresContent(ScoreManager.getEntriesSortedByScoreDescending());
            ScoreManager.unloadEntries();
        }
    }
    static class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("BAck");
            ((MenuPanel)GUIManager.getPanel("menu")).showButtons(true);
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
