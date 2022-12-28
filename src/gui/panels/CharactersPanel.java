package gui.panels;

import data.Enemy;
import main.MainApp;
import managers.GUIManager;

import java.awt.*;

public class CharactersPanel extends CustomPanel{

    final int CHAR_X = 200;
    final int CHAR_Y = 288;

    Image playerTexture;
    Image enemyTexture;
    Image prevEnemyTexture;

    int prevEnemyX;
    int prevEnemyY;

    public CharactersPanel(){
        super();
        this.setOpaque(false);
        this.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
        playerTexture = MainApp.getPlayer().getPlayerTexture().getImage();

        this.setVisible(true);
    }
    public void updatePrevEnemy(){

        prevEnemyTexture = enemyTexture;
        prevEnemyX = MainApp.getEnemy().getLocation().x;
        prevEnemyY = MainApp.getEnemy().getLocation().y;

    }

    public void updateEnemyTexture(){
        enemyTexture = MainApp.getEnemy().getEnemyTexture().getImage();
    }

    public void moveEnemies(double dT){
        double distance = dT* Enemy.MOVE_SPEED;
        prevEnemyX -= distance;
        MainApp.getEnemy().moveEnemy(distance);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(playerTexture, MainApp.getPlayer().getLocation().x, MainApp.getPlayer().getLocation().y, CHAR_X, CHAR_Y, null);
        g2.drawImage(enemyTexture, MainApp.getEnemy().getLocation().x ,MainApp.getEnemy().getLocation().y , CHAR_X, CHAR_Y, null);
        g2.drawImage(prevEnemyTexture, prevEnemyX, prevEnemyY, CHAR_X, CHAR_Y, null);


        g2.dispose();
    }
}
