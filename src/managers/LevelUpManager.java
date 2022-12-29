package managers;

import data.Texture;
import gui.panels.LevelUpPanel;
import interfaces.Interactible;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelUpManager {

    public static class UpgradeStatsButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.nextState();
        }
    }

    public static class ConfirmButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            StateMachine.nextState();
        }
    }

    private static final UpgradeStatsButtonListener upgradeStatsButtonListener = new UpgradeStatsButtonListener();
    private static final ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();

    public static void initStatsButtons(){
        //Iterating over all upgradable stats
        for (int i = 0; i < 3; i++) {
            Texture statTexture = new Texture(ResourceManager.getTexture("stat" + i).getTexturePath());
            ((LevelUpPanel) GUIManager.getPanel("levelup")).addStatButton(statTexture, "stat" + i);
            ((Interactible) GUIManager.getPanel("levelup")).addButtonListener(upgradeStatsButtonListener, "stat" + i);
        }
    }

    public static void init() {
        initStatsButtons();
        ((LevelUpPanel) GUIManager.getPanel("levelup")).update();
    }


}
