package gui.panels.Fight;

import gui.panels.CustomPanel;
import managers.FightManager;
import managers.GUIManager;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.Texture;
import data.Enemy.Enemy;
import main.MainApp;

public class FightPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;


    public FightPanel(){
        super();
        this.setBackground(Color.magenta);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(FightManager.getConfirmButton());

        
        

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        MainApp.getPlayer().draw(g2);
        Enemy enemy = new Enemy();
        enemy.draw(g2);

        g2.dispose();
    }

    



}
