package data;

import java.util.*;

import javax.imageio.ImageIO;


import data.Character;
import data.Inventory;
import data.Texture;
import managers.ResourceManager;


public class Player extends Character {

    private Inventory inventory;
    public ArrayList<Texture> animation ;
    public Texture currentTexture;
    private int maxHealth;

    private int Level;

    public Player() {
        super();
        currentTexture = ResourceManager.getTexture("player");
        getLocation().x = 70;
        getLocation().y = 340;
        maxHealth = 100;
        this.Level = 1;
        this.inventory = new Inventory();

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
}
