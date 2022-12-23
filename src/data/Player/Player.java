package data.Player;

import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.awt.Graphics2D;

import data.Character;
import data.Inventory;


public class Player extends Character {
    private Inventory inventory = new Inventory();
    public BufferedImage position1, position2, position3, position4;

    public Player() {
        super();
        getPlayerImage();

    }

    public void getPlayerImage() {
        try {
            position1 = ImageIO.read(getClass().getResourceAsStream("player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = position1;

        //cases needed for anim
        g2.drawImage(image, 50, 50, 100, 100, null);
    }

    public Inventory getInventory() {
        return inventory;
    }

}
