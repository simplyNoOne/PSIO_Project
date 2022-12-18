package gui.panels.Fight;

import gui.panels.CustomPanel;
import managers.FightManager;
import managers.GUIManager;

import java.awt.*;
import java.awt.event.ActionListener;

public class FightPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;

    public FightPanel(){
        super();
        this.setBackground(Color.magenta);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(FightManager.getConfirmButton());

    }

}
