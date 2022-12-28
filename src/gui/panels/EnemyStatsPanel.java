package gui.panels;

import main.MainApp;
import managers.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EnemyStatsPanel extends StatsPanel{
    JLabel nameVal;
    JLabel currentHealthVal;
    JLabel armorVal;
    public EnemyStatsPanel(){

        super();
        this.setBounds(GUIManager.getWidth() - PANEL_WIDTH - 30, 10, PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);

        JLabel nameTitle = new TitleLabel("Name: ");
        JLabel currentHealthTitle = new TitleLabel("Current Health: ");
        JLabel armorTitle =new TitleLabel("Armor: ");

        nameVal = new ValueLabel();
        currentHealthVal = new ValueLabel();
        armorVal =new ValueLabel();


        nameTitle.setBounds(20, 25, 100, 25);
        currentHealthTitle.setBounds(20, 75, 100, 25);
        armorTitle.setBounds(20, 125, 100, 25);

        nameVal.setBounds(80, 25, 200, 25);
        currentHealthVal.setBounds(120, 75, 200, 25);
        armorVal.setBounds(100, 125, 200, 25);



        armorTitle.setBackground(Color.green);
        armorVal.setBackground(Color.orange);

        this.add(nameTitle);
        this.add(nameVal);
        this.add(currentHealthTitle);
        this.add(currentHealthVal);
        this.add(armorTitle);
        this.add(armorVal);
    }

    public void updateStats(){
        nameVal.setText(MainApp.getEnemy().getName());
        currentHealthVal.setText(Integer.toString(MainApp.getEnemy().getHealth()));
        armorVal.setText(Integer.toString(MainApp.getEnemy().getArmor()));
        this.revalidate();
    }
}
