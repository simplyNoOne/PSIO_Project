package managers;

import gui.panels.MenuPanel;
import gui.panels.ScoresPanel;
import interfaces.Interactible;
import main.MainApp;
import main.ManagerHandler;
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
            String nickname = ((MenuPanel) ManagerHandler.getGUIManager().getPanel("menu")).getNickField().getText();
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

            ((MenuPanel)ManagerHandler.getGUIManager().getPanel("menu")).showButtons(false);
            fillScoresPanel();
            ManagerHandler.getGUIManager().addPanel("scores", "menu");
            ManagerHandler.getGUIManager().getPane("menu").repaint();

            ManagerHandler.getScoreManager().loadEntries();
            System.out.println(ManagerHandler.getScoreManager().getEntriesSortedByScoreDescending());
            ManagerHandler.getScoreManager().unloadEntries();
            ((MenuPanel)ManagerHandler.getGUIManager().getPanel("menu")).revalidate();
            ((MenuPanel)ManagerHandler.getGUIManager().getPanel("menu")).repaint();
        }

        private void fillScoresPanel() {
            ManagerHandler.getScoreManager().loadEntries();
            ((ScoresPanel) ManagerHandler.getGUIManager().getPanel("scores")).setScoresContent(ManagerHandler.getScoreManager().getEntriesSortedByScoreDescending());
            ManagerHandler.getScoreManager().unloadEntries();
        }
    }
    static class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("BAck");
            ((MenuPanel)ManagerHandler.getGUIManager().getPanel("menu")).showButtons(true);
            ManagerHandler.getGUIManager().removePanel("scores", "menu");
            ManagerHandler.getGUIManager().getPane("menu").repaint();

        }
    }

    private final StartListener startListener = new StartListener();
    private final QuittListener quittListener = new QuittListener();
    private final ScoresListener scoresListener = new ScoresListener();
    private final BackListener backListener = new BackListener();

    public void init(){
        ((Interactible)ManagerHandler.getGUIManager().getPanel("menu")).addButtonListener(startListener, "start");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("menu")).addButtonListener(quittListener, "quit");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("menu")).addButtonListener(scoresListener, "scores");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("scores")).addButtonListener(backListener, "");

    }





}
