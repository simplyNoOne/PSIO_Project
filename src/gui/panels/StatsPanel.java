package gui.panels;

import javax.swing.*;
import java.awt.*;

abstract public class StatsPanel extends CustomPanel{


    protected static int PANEL_WIDTH = 200;
    protected static int PANEL_HEIGHT = 250;


    JLabel nameVal;

    JLabel levelVal;
    JLabel maxHealthVal;
    JLabel currentHealthVal;
    JLabel armorVal;
    JLabel baseDamageVal;
    JLabel critChanceVal;
    JLabel dodgeChanceVal;

    JLabel nameTitle;
    JLabel levelTitle;
    JLabel maxHealthTitle;
    JLabel currentHealthTitle ;
    JLabel armorTitle ;
    JLabel baseDamageTitle;
    JLabel critChanceTitle;
    JLabel dodgeChanceTitle;

    public class ValueLabel extends JLabel {
        ValueLabel(){
            this.setForeground(Color.white);
            this.setSize(200, 25);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
            StatsPanel.this.add(this);
        }
    }

    public class TitleLabel extends JLabel {
        TitleLabel(String text){
            this.setForeground(Color.white);
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
            this.setText(text);
            this.setSize(100, 25);
            StatsPanel.this.add(this);
        }
    }

    StatsPanel(){
        super();
        this.setLayout(null);

        nameVal = new ValueLabel();
        levelVal = new ValueLabel();
        maxHealthVal= new ValueLabel();
        currentHealthVal = new ValueLabel();
        armorVal = new ValueLabel();
        baseDamageVal = new ValueLabel();
        critChanceVal = new ValueLabel();
        dodgeChanceVal = new ValueLabel();

        nameTitle = new TitleLabel("Name: ");
        levelTitle = new TitleLabel("Level: ");
        maxHealthTitle = new TitleLabel("Max Health: ");
        currentHealthTitle = new TitleLabel("Current Health: ");
        armorTitle =new TitleLabel("Armor: ");
        baseDamageTitle = new TitleLabel("Base Damage: ");
        critChanceTitle = new TitleLabel("Critical Chance: ");
        dodgeChanceTitle =new TitleLabel("Dodge Chance: ");


        this.setBackground(new Color(0, 0, 0, 150));

    }

    public abstract void updateStats();

}
