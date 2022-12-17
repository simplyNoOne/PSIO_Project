package main;

import controller.Managers.ResourceManager;
import controller.Managers.Texture;
import data.Collectible;
import data.Player;
import data.Weapon;
import gui.GUIManager;

import javax.swing.*;
import java.awt.*;
import java.nio.FloatBuffer;

public class Main{
    private final static int FPS = 120;
    private final static double SEC_IN_NANOS = 1_000_000_000.0;

    static State currentState;

    private static JFrame gameFrame;

    private static Player player;

    private static void runApp(){
        double timePerFrame =  SEC_IN_NANOS / FPS;
        long lastFrame = System.nanoTime();
        long now;
        long dT;
        while(true) {
            now = System.nanoTime();
            dT = now - lastFrame;
            if(dT  >= timePerFrame) {
                //currentState.update(dT/ SEC_IN_NANOS);

                gameFrame.repaint();
                lastFrame = now;
            }

        }
    }

    // must stay here
    public static void main (String [] args) {
        initialize();
        runApp();
    }

    public static void initialize(){
        player = new Player();

        loadAllResources();

        GUIManager.initAllPanels();
        gameFrame = new JFrame("THE LAS OF US");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setBounds(GUIManager.getPosX(), GUIManager.getPosY(), GUIManager.getWidth(), GUIManager.getHeight() + gameFrame.getInsets().top);
        GUIManager.addPanel("puzzleOrFight");
        gameFrame.setVisible(true);

    }

    public static JFrame getGameFrame(){
        return gameFrame;
    }

    public static Player getPlayer()
    {
        return player;
    }

    public static void loadAllResources()
    {
        //Add some weapons to the Players Inventory

        Weapon testWeapon1 = new Weapon();
        Texture testWeaponTexture1 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\hammer.png");
        ResourceManager.addTexture("hammer", testWeaponTexture1);
        testWeapon1.setName("hammer");
        player.getInventory().addWeapon(testWeapon1);

        Weapon testWeapon2 = new Weapon();
        Texture testWeaponTexture2 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\sword.png");
        ResourceManager.addTexture("sword", testWeaponTexture2);
        testWeapon2.setName("sword");
        player.getInventory().addWeapon(testWeapon2);

        //Add some collectibles to the Players Inventory

        Collectible testCollectible1 = new Collectible();
        Texture testCollectibleTexture1 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\health_potion.png");
        ResourceManager.addTexture("health_potion", testCollectibleTexture1);
        testCollectible1.setName("health_potion");
        player.getInventory().addCollectible(testCollectible1);

        Collectible testCollectible2 = new Collectible();
        Texture testCollectibleTexture2 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\throwable_poison.png");
        ResourceManager.addTexture("throwable_poison", testCollectibleTexture2);
        testCollectible2.setName("throwable_poison");
        player.getInventory().addCollectible(testCollectible2);

        Collectible testCollectible3 = new Collectible();
        Texture testCollectibleTexture3 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\sultans_fart.png");
        ResourceManager.addTexture("sultans_fart", testCollectibleTexture3);
        testCollectible3.setName("sultans_fart");
        player.getInventory().addCollectible(testCollectible3);



        Texture testConfirmTexture1 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\confirm1.png");
        ResourceManager.addTexture("confirm1", testConfirmTexture1);

        Texture testConfirmTexture2 =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\confirm2.png");
        ResourceManager.addTexture("confirm2", testConfirmTexture2);

        Texture testCarryOutTheFight =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\carry_out_the_fight.png");
        ResourceManager.addTexture("carry_out_the_battle", testCarryOutTheFight);

        Texture fightTexture =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\fight.png");
        ResourceManager.addTexture("fight", fightTexture);

        Texture puzzleTexture =  new Texture("C:\\Users\\Jędrzej\\OneDrive - Politechnika Wroclawska\\Dokumenty\\GitHub\\PSIO_Project\\resources\\textures\\puzzle.png");
        ResourceManager.addTexture("puzzle", puzzleTexture);
    }

}
