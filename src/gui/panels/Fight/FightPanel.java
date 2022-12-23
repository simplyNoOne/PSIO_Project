package gui.panels.Fight;

import gui.panels.CustomPanel;
import managers.FightManager;
import managers.GUIManager;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import data.Enemy;
import data.Texture;
import main.MainApp;

public class FightPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;


    public FightPanel(){
        super();
        this.setBackground(Color.magenta);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(FightManager.getConfirmButton());

        Texture player_texture = MainApp.getPlayer().getTexture(0);
        JButton player = new JButton(player_texture);
        int x0 = 0;
        int y0 = 0;
        player.setBounds(x0, y0, player_texture.getIconWidth(), player_texture.getIconHeight());
        this.add(player);

        Enemy enemy = new Enemy();
        Texture enemy_texture = enemy.getEnemy().getTexture("default");
        JButton enemy_button = new JButton(enemy_texture);
        int x_enemy = 400;
        int y_enemy = 0;
        enemy_button.setBounds(x_enemy, y_enemy, enemy_texture.getIconWidth(), enemy_texture.getIconHeight());
        this.add(enemy_button);
        



    }

}
