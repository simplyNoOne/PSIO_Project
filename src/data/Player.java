package data;

import java.util.*;

import javax.imageio.ImageIO;


import data.Character;
import data.Inventory;
import data.Texture;
import managers.ResourceManager;


public class Player extends Character {
    private final int BASE_HOVER_HEIGHT = 350;

    private int dirMultp = 1;
    private double diff;
    private int score;
    private Inventory inventory;
    public Texture currentTexture;
    private int maxHealth;

    private Weapon activeWeapon;
    private Collectible activeCollectible;

    private int Level;

    public Player() {
        super();
        currentTexture = ResourceManager.getTexture("player");
        getLocation().x = 70;
        getLocation().y = BASE_HOVER_HEIGHT;
        maxHealth = 100;
        this.Level = 1;
        this.inventory = new Inventory();
        this.score = 0;

    }

    public Texture getPlayerTexture() {
        return currentTexture;

    }

    public int getMaxHealth(){return maxHealth;}

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getLevel() {
        return Level;
    }

    public void increaseLevel() {
        Level += 1;
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
                activeCollectible = collectible;
                break;
            }
    }

    public void resetActiveCollectible(){activeCollectible = null;}

    public Collectible getActiveCollectible(){return activeCollectible;}

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
