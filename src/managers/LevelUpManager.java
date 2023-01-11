package managers;

import data.Player;
import data.Texture;
import gui.panels.LevelUpPanel;
import gui.panels.StatsPanel;
import interfaces.Interactible;
import main.ManagerHandler;
import main.MainApp;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelUpManager {

    public static class LevelUpButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (((LevelUpPanel.LevelUpButton) (e.getSource())).getName()) {
                case "stat0" -> {
                    physicalGrowth();
                } case "stat1" -> {
                    dexterityTraining();
                } case "stat2" -> {
                    armorEnhancement();
                }
            }

            System.out.println(((LevelUpPanel.LevelUpButton) (e.getSource())).getText() + " button clicked!");
            ((StatsPanel) ManagerHandler.getGUIManager().getPanel("playerStats")).updateStats();

            StateMachine.nextState();
        }
    }

    private static final LevelUpButtonListener levelUpButtonListener = new LevelUpButtonListener();

    private static void initStatsButtons(){
        //Iterating over all upgradable stats

        // wrapping hints with HTML to easily represent newlines in JLabels
        String hint = "<html>PHYSICAL GROWTH: <br><br>Increase max health (+30%), increase base damage (+50%)</html>";
        ((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addChoiceHint(hint, "stat0");

        hint = "<html>DEXTERITY TRAINING: <br><br>Increase dodge chance (+30%), critical chance (+20%)</html>";
        ((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addChoiceHint(hint, "stat1");

        hint = "<html>ARMOR ENHANCEMENT: <br><br>Improve armor (+100%)</html>";
        ((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addChoiceHint(hint, "stat2");

        for (int i = 0; i < 3; i++) {
            Texture texture = new Texture(ManagerHandler.getResourceManager().getTexture("stat" + i).getTexturePath());
            //((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addStatButton(statTexture, "stat" + i);
            //((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addStatButton(Integer.toString(i + 1), "stat" + i);
            ((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).addButton(texture, "stat" + i);

            ((Interactible) ManagerHandler.getGUIManager().getPanel("levelup")).addButtonListener(levelUpButtonListener, "stat" + i);
        }
    }

    public void init() {
        initStatsButtons();
        ((LevelUpPanel) ManagerHandler.getGUIManager().getPanel("levelup")).update();
    }

    public static void generalLevelUp() {
        Player player = MainApp.getPlayer();
        player.increaseLevel(); // +1
        player.setHealth(player.getMaxHealth()); // regain life

        ((StatsPanel) ManagerHandler.getGUIManager().getPanel("playerStats")).updateStats(); // update the level counter for the user
    }

    /** Physical growth: increase max health (+30%), increase base damage (+50%) **/
    private static void physicalGrowth() {
        Player player = MainApp.getPlayer();
        player.setMaxHealth((int) (1.3 * player.getMaxHealth()));
        player.setHealth(player.getMaxHealth()); // regain life (updated max health)

        player.setBaseDamage((int) (1.5 * player.getBaseDamage()));
    }

    /** Dexterity training: dodge chance (+30%), critical chance (+20%) **/
    private static void dexterityTraining() {
        Player player = MainApp.getPlayer();
        player.setDodgeChance((int) (1.3 * player.getDodgeChance()));
        player.setCriticalChance((int) (1.2 * player.getCriticalChance()));
    }

    /** Armor enhancement: armor (+100%) **/
    private static void armorEnhancement() {
        Player player = MainApp.getPlayer();
        player.setArmor((int) (2.0 * player.getArmor()));
    }

}
