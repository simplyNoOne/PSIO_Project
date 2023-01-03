package gui.panels;

import main.MainApp;
import managers.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerStatsPanel extends StatsPanel{


    JLabel nameVal;
    JLabel maxHealthVal;
    JLabel currentHealthVal;
    JLabel armorVal;
    public PlayerStatsPanel(){

        super();
        this.setBounds(10, 10, PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);

        JLabel nameTitle = new TitleLabel("Name: ");
        JLabel maxHealthTitle = new TitleLabel("Max Health: ");
        JLabel currentHealthTitle = new TitleLabel("Current Health: ");
        JLabel armorTitle =new TitleLabel("Armor: ");

        nameVal = new ValueLabel();
        nameVal.setText(MainApp.getPlayer().getName());
        maxHealthVal= new ValueLabel();
        currentHealthVal = new ValueLabel();
        armorVal =new ValueLabel();


        nameTitle.setBounds(20, 25, 100, 25);
        maxHealthTitle.setBounds(20, 75, 100, 25);
        currentHealthTitle.setBounds(20, 125, 100, 25);
        armorTitle.setBounds(20, 175, 100, 25);

        nameVal.setBounds(80, 25, 200, 25);
        maxHealthVal.setBounds(100, 75, 200, 25);
        currentHealthVal.setBounds(120, 125, 200, 25);
        armorVal.setBounds(100, 175, 200, 25);



        armorTitle.setBackground(Color.green);
        armorVal.setBackground(Color.orange);

        this.add(nameTitle);
        this.add(nameVal);
        this.add(maxHealthTitle);
        this.add(maxHealthVal);
        this.add(currentHealthTitle);
        this.add(currentHealthVal);
        this.add(armorTitle);
        this.add(armorVal);
    }

    public void updateStats(){
        maxHealthVal.setText(Integer.toString(MainApp.getPlayer().getMaxHealth()));
        currentHealthVal.setText(Integer.toString(MainApp.getPlayer().getHealth()));
        armorVal.setText(Integer.toString(MainApp.getPlayer().getArmor()));
        this.revalidate();
        this.repaint();
    }

}
