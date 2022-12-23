package data.Enemy;


import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;




import data.Character;

public class Enemy extends Character
{
    private boolean canPuzzle;
    private String abilityName;
    public BufferedImage common , boss;

    public Enemy() {
        super();
        this.canPuzzle =  false;
        this.abilityName = "None";
        getEnemyImage();
       

    }

    public Enemy(String name, int health, int armor, int baseDamage, int dodgeChance, boolean canPuzzle, String abilityName)
    {
        super(name, health, armor, baseDamage, dodgeChance);
        this.canPuzzle = canPuzzle;
        this.abilityName = abilityName;
        getEnemyImage();

    }

    public void getEnemyImage() {
        try {
            common = ImageIO.read(getClass().getResourceAsStream("enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = common;
        //cases needed for boss and common
        g2.drawImage(image, 300, 0, 100, 100 , null);
    }

   
    


    public Enemy getEnemy(){
        return this;
    }

    public boolean isCanPuzzle() {
        return canPuzzle;
    }

    public void setCanPuzzle(boolean canPuzzle) {
        this.canPuzzle = canPuzzle;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
