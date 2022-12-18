package gui.panels;

import managers.GUIManager;

import java.awt.event.ActionListener;

public class EnemyStatsPanel extends StatsPanel{
    public EnemyStatsPanel(){

        super();
        this.setBounds(GUIManager.getWidth() - PANEL_WIDTH - 10, 10, PANEL_WIDTH, PANEL_HEIGHT);
    }
}
