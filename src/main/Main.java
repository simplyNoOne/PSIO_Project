package main;

import gui.GUIManager;

import javax.swing.*;
import java.awt.*;

public class Main{
    private final static int FPS = 120;
    private final static double SEC_IN_NANOS = 1_000_000_000.0;


    static State currentState;

    private static JFrame gameFrame;

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
        GUIManager.initAllPanels();
        gameFrame = new JFrame("THE LAS OF US");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setBounds(GUIManager.getPosX(), GUIManager.getPosY(), GUIManager.getWidth(), GUIManager.getHeight());
        GUIManager.addPanel("menu");
        gameFrame.setVisible(true);

    }

    public static JFrame getGameFrame(){
        return gameFrame;
    }

}
