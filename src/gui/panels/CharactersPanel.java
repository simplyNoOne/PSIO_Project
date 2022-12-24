package gui.panels;

import main.MainApp;
import managers.GUIManager;

import java.awt.*;

public class CharactersPanel extends CustomPanel{


    Image playerTexture;
   Image enemyTexture;

    public CharactersPanel(){
        super();
        this.setOpaque(false);
        this.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
        playerTexture = MainApp.getPlayer().getPlayerTexture().getImage();

        this.setVisible(true);
    }
    public void updateEnemy(){
        enemyTexture = MainApp.getEnemy().getEnemyTexture().getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(playerTexture, MainApp.getPlayer().getLocation().x, MainApp.getPlayer().getLocation().y, 100, 100, null);
        g2.drawImage(enemyTexture, MainApp.getEnemy().getLocation().x ,MainApp.getEnemy().getLocation().y , 100, 100, null);


        g2.dispose();
    }
}
