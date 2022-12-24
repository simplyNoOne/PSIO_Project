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

    public Player() {
        super();
        currentTexture = ResourceManager.getTexture("player");
        getLocation().x = 100;
        getLocation().y = 400;

    }

    public Texture getPlayerTexture() {
        return currentTexture;

    }



    public Inventory getInventory() {
        return inventory;
    }

}
