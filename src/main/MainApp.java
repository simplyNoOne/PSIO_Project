package main;

import data.Collectible;
import data.Player;
import data.Weapon;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;

public class MainApp {
    private final static int FPS = 120;
    private final static double SEC_IN_NANOS = 1_000_000_000.0;
    private static JFrame gameFrame;
    private static Player player;

    private static void runApp(){
        double timePerFrame =  SEC_IN_NANOS / FPS;
        long lastFrame = System.nanoTime();
        long now;
        long dT;
        while(StateMachine.getCurrentState() != StateMachine.State.END) {
            now = System.nanoTime();
            dT = now - lastFrame;
            if(dT  >= timePerFrame) {
                //currentState.update(dT/ SEC_IN_NANOS);
                StateMachine.update(dT/SEC_IN_NANOS);
                gameFrame.repaint();
                lastFrame = now;
            }

        }
        ResourceManager.unloadResources();
        System.exit(0);
    }

    // must stay here
    public static void main (String [] args) {
        initialize();
        runApp();
    }

    public static void initialize(){
        player = new Player();
        gameFrame = new JFrame("THE LAS OF US");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setBounds(GUIManager.getPosX(), GUIManager.getPosY(), GUIManager.getWidth(), GUIManager.getHeight());
        gameFrame.setResizable(false);
    }

    public static JFrame getGameFrame(){
        return gameFrame;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void loadAllResources()
    {
        //Add some weapons to the Players Inventory

        Weapon testWeapon1 = new Weapon();
        testWeapon1.setName("hammer");
        player.getInventory().addWeapon(testWeapon1);

        Weapon testWeapon2 = new Weapon();
        testWeapon2.setName("sword");
        player.getInventory().addWeapon(testWeapon2);

        //Add some collectibles to the Players Inventory

        Collectible testCollectible1 = new Collectible();
        testCollectible1.setName("health_potion");
        player.getInventory().addCollectible(testCollectible1);

        Collectible testCollectible2 = new Collectible();
        testCollectible2.setName("throwable_poison");
        player.getInventory().addCollectible(testCollectible2);

        Collectible testCollectible3 = new Collectible();
        testCollectible3.setName("sultans_fart");
        player.getInventory().addCollectible(testCollectible3);


    }

}
