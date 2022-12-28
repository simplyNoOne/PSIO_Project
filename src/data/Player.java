package data;

import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.awt.Graphics2D;

import data.Character;
import data.Inventory;
import data.Texture;
import managers.ResourceManager;


public class Player extends Character {

    private Inventory inventory = new Inventory();
    public ArrayList<Texture> animation ;
    public Texture currentTexture;
    private int maxHealth;

    public Player() {
        super();
        currentTexture = ResourceManager.getTexture("player");
        getLocation().x = 70;
        getLocation().y = 340;
        maxHealth = 100;

    }

    public Texture getPlayerTexture() {
        return currentTexture;

    }

    public int getMaxHealth(){return maxHealth;}



    public Inventory getInventory() {
        return inventory;
    }

}
