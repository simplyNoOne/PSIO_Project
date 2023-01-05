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
    public ArrayList<Texture> animation ;
    public Texture currentTexture;
    private int maxHealth;

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



    public Inventory getInventory() {
        return inventory;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

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
