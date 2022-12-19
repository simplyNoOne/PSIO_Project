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

                StateMachine.update(dT/SEC_IN_NANOS);
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
    }//
    public static void spawnPlayer(){

        player = new Player();
    }

}
