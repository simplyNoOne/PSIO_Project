package gui.panels;

import main.MainApp;
import javax.swing.*;

public class PlayerStatsPanel extends StatsPanel{

    JLabel levelVal;
    JLabel maxHealthVal;

    public PlayerStatsPanel(){

        super();
        this.setBounds(10, 10, PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);

        JLabel levelTitle = new TitleLabel("Level: ");
        JLabel maxHealthTitle = new TitleLabel("Max Health: ");


        levelVal = new ValueLabel();
        maxHealthVal= new ValueLabel();


        nameTitle.setLocation(20, 25);
        levelTitle.setLocation(20, 50);
        maxHealthTitle.setLocation(20, 75);
        currentHealthTitle.setLocation(20, 100);
        armorTitle.setLocation(20, 125);
        baseDamageTitle.setLocation(20, 150);
        critChanceTitle.setLocation(20, 175);
        dodgeChanceTitle.setLocation(20, 200);

        nameVal.setLocation(70, 25);
        levelVal.setLocation(70, 50);
        maxHealthVal.setLocation(100, 75);
        currentHealthVal.setLocation(120, 100);
        armorVal.setLocation(70, 125);
        baseDamageVal.setLocation(120, 150);
        critChanceVal.setLocation(120, 175);
        dodgeChanceVal.setLocation(120, 200);


    }

    public void updateStats(){
        nameVal.setText(MainApp.getPlayer().getName());
        levelVal.setText(Integer.toString(MainApp.getPlayer().getLevel()));
        maxHealthVal.setText(Integer.toString(MainApp.getPlayer().getMaxHealth()));
        currentHealthVal.setText(Integer.toString(MainApp.getPlayer().getHealth()));
        armorVal.setText(Integer.toString(MainApp.getPlayer().getArmor()));
        baseDamageVal.setText(Integer.toString(MainApp.getPlayer().getBaseDamage()));
        critChanceVal.setText(Integer.toString(MainApp.getPlayer().getCriticalChance()));
        dodgeChanceVal.setText(Integer.toString(MainApp.getPlayer().getDodgeChance()));
        this.revalidate();
    }

}
