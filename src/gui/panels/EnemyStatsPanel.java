package gui.panels;

import main.MainApp;
import managers.GUIManager;


public class EnemyStatsPanel extends StatsPanel{

    public EnemyStatsPanel(){

        super();
        this.setBounds(GUIManager.getWidth() - PANEL_WIDTH - 30, 10, PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);


        nameTitle.setLocation(20, 25);
        currentHealthTitle.setLocation(20, 50);
        armorTitle.setLocation(20, 75);
        baseDamageTitle.setLocation(20, 100);
        critChanceTitle.setLocation(20, 125);
        dodgeChanceTitle.setLocation(20, 150);


        nameVal.setLocation(70, 25);
        currentHealthVal.setLocation(120, 50);
        armorVal.setLocation(70, 75);
        baseDamageVal.setLocation(120, 100);
        critChanceVal.setLocation(120, 125);
        dodgeChanceVal.setLocation(120, 150);


    }

    public void updateStats(){
        nameVal.setText(MainApp.getEnemy().getName());
        currentHealthVal.setText(Integer.toString(MainApp.getEnemy().getHealth()));
        armorVal.setText(Integer.toString(MainApp.getEnemy().getArmor()));
        baseDamageVal.setText(Integer.toString(MainApp.getEnemy().getBaseDamage()));
        critChanceVal.setText(Integer.toString(MainApp.getEnemy().getCriticalChance()));
        dodgeChanceVal.setText(Integer.toString(MainApp.getEnemy().getDodgeChance()));
        this.revalidate();
    }
}
