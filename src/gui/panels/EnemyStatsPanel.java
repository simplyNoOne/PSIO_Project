package gui.panels;

import main.MainApp;
import managers.GUIManager;


public class EnemyStatsPanel extends StatsPanel{

    public EnemyStatsPanel(){

        super();
        this.setBounds(GUIManager.getWidth() - PANEL_WIDTH - 30, 10, PANEL_WIDTH, PANEL_HEIGHT);


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

        levelVal.setText("???");

    }

    public void updateStats(){
        nameVal.setText(MainApp.getEnemy().getName());
        maxHealthVal.setText(Integer.toString(MainApp.getEnemy().getInitialHealth()));
        currentHealthVal.setText(Integer.toString(MainApp.getEnemy().getHealth()));
        armorVal.setText(Integer.toString(MainApp.getEnemy().getArmor()));
        baseDamageVal.setText(Integer.toString(MainApp.getEnemy().getBaseDamage()));
        critChanceVal.setText(Integer.toString(MainApp.getEnemy().getCriticalChance()));
        dodgeChanceVal.setText(Integer.toString(MainApp.getEnemy().getDodgeChance()));
        this.revalidate();
    }
}
