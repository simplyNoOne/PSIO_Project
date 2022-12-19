package managers;

import data.Texture;
import gui.panels.LevelUp.buttons.UpgradeStatsButton;
import main.StateMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LevelUpManager {

    private static final int TITLE_BAR_HEIGHT = 31;
    private static final UpgradeStatsButtonListener uprgradeStatsButtonListener = new UpgradeStatsButtonListener();

    public static class UpgradeStatsButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Upgrade stats button has been clicked!");
            StateMachine.nextState();
        }
    }


    public static ArrayList<UpgradeStatsButton> getUpgradeStatsButtons()
    {
//      TODO in here I'll specify which stats will be upgraded + Stats will be displayed as a layers on this panel
        ArrayList<UpgradeStatsButton> upgradeStatsButtonList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Texture statTexture = new Texture(ResourceManager.getTexture("stat" + i).getTexturePath());
            int x0 = (GUIManager.getWidth() - statTexture.getIconWidth()) / 2;
            int y0 = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - statTexture.getIconHeight()) / 2;
            UpgradeStatsButton upgradeStatsButton = new UpgradeStatsButton(statTexture);
            upgradeStatsButton.setBounds(x0, y0, statTexture.getIconWidth(), statTexture.getIconHeight());
            upgradeStatsButton.addActionListener(uprgradeStatsButtonListener);
            upgradeStatsButtonList.add(upgradeStatsButton);
        }

        return upgradeStatsButtonList;
    }


}
