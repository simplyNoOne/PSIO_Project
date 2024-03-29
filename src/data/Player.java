package data;

import java.util.*;

import javax.imageio.ImageIO;


import data.Character;
import data.Inventory;
import data.Texture;
import main.ManagerHandler;
import managers.ResourceManager;


public class Player extends Character {
    private final int BASE_HOVER_HEIGHT = 350;

    private int dirMultp = 1;
    private double diff;
    private int score;
    private Inventory inventory;
    public Texture currentTexture;
    private int maxHealth;
    private int enemiesApproached;

    private Weapon activeWeapon;
    private ArrayList<Collectible> activeCollectibles;
    

    private int Level;

    public Player() {
        super();
        currentTexture = ManagerHandler.getResourceManager().getTexture("player");
        getLocation().x = 70;
        getLocation().y = BASE_HOVER_HEIGHT;
        maxHealth = 100;
        this.Level = 1;
        this.inventory = new Inventory();
        this.score = 0;
        this.enemiesApproached = 0;
        this.activeCollectibles = new ArrayList<>();

    }

    public Texture getPlayerTexture() {
        return currentTexture;

    }

    public int getMaxHealth(){return maxHealth;}

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getEnemiesApproached(){return enemiesApproached;}
    public void setEnemiesApproached(int enemiesApproached){this.enemiesApproached = enemiesApproached;}
    public Inventory getInventory() {
        return inventory;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void increaseLevel() {
        Level += 1;
    }

    public int getScore(){return score;}

    public void modifyScore(int modifier){
        score += modifier;
        if(score < 0)
            score = 0;
    }


    public void increase_health(double value){
        if(this.getHealth()*value>=this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
        else this.setHealth((int) Math.round(this.getHealth()*value));
    }

    public void increase_damage(double value){
        this.setBaseDamage((int) Math.round(this.getBaseDamage()*value));
    }

    public void increase_dodge(double value){
        this.setDodgeChance((int) Math.round(this.getDodgeChance()*value));
    }

    public void increase_critical(double value){
        this.setCriticalChance((int) Math.round(this.getCriticalChance()*value));
    }

    public void increase_armour(double value){
        this.setArmor((int) Math.round(this.getArmor()*value));
    }


    public void setActiveWeapon(String weaponName){
        for(Weapon weapon : this.getInventory().getWeapons())
            if(weapon.getName().equals(weaponName)) {
                activeWeapon = weapon;
                break;
            }
    }
    public void resetActiveWeapon(){activeWeapon = null;}
    public Weapon getActiveWeapon(){return activeWeapon;}
    public void setActiveCollectible(String collectibleName){
        for(Collectible collectible : this.getInventory().getCollectibles())
            if(collectible.getName().equals(collectibleName)) {
                activeCollectibles.add(collectible);
                break;
            }
    }

    public void resetActiveCollectible(String collectibleName){
        activeCollectibles.removeIf((Collectible col) -> col.getName().equals(collectibleName));
    }

    public void clearActiveCollectibles(){activeCollectibles.clear();}

    public ArrayList<Collectible> getActiveCollectible(){return activeCollectibles;}

    public void hover(int distance){
        double temp = distance/4.5;
         diff += temp;
         if(diff > 1) {
             getLocation().y += diff * dirMultp;
             if (getLocation().y > BASE_HOVER_HEIGHT + 20)
                 dirMultp = -1;
             if (getLocation().y < BASE_HOVER_HEIGHT - 20)
                 dirMultp = 1;
             diff = 0;
         }

    }
}
