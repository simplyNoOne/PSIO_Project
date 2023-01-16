package main;

import data.Collectible;
import data.Enemy;
import data.Player;
import managers.GUIManager;

import javax.swing.*;

public class MainApp implements Runnable{
    private final static int FPS = 60;
    private final static double SEC_IN_NANOS = 1_000_000_000.0;
    private static JFrame gameFrame;
    private static Player player;
    private static Enemy enemy;
    

    private void runApp(){

        Thread gameThread = new Thread(this);
        gameThread.run();
    }

    // must stay here
    public static void main (String [] args) {

        MainApp game = new MainApp();

        game.initialize();
        game.runApp();
    }

    public void initialize(){
        StateMachine.setCurrentState(StateMachine.State.START);
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
    public static Enemy getEnemy(){return enemy;}
    public static void setEnemy(Enemy e){enemy = e; }

    public static void spawnPlayer() {

        player = new Player();
        //player.getInventory().addWeapon(new Weapon("sword", 10, 10));
        //player.getInventory().addWeapon(new Weapon("axe", 19, 1));
        //player.getInventory().addWeapon(new Weapon("dagger", 1, 19));
        player.getInventory().addToInventory(new Collectible("collectible1" , 1.2));
        player.getInventory().addToInventory(new Collectible("collectible2" , 1.2));
        player.getInventory().addToInventory(new Collectible("collectible3",  1.3));
        player.getInventory().addToInventory(new Collectible("collectible4" , 1.2));
        player.getInventory().addToInventory(new Collectible("collectible5" , 1.3));

    }
    @Override
    public void run() {
        double timePerFrame =  SEC_IN_NANOS / FPS;
        long end = System.nanoTime();
        long now;
        long begin;
        long dT;
        int iters = 0;
        while(StateMachine.getCurrentState() != StateMachine.State.END) {
            if(iters == 1800){
                System.gc();
                iters = 0;
            }
            iters++;
            begin = now = System.nanoTime();
            dT = now - end;
            StateMachine.update( dT/SEC_IN_NANOS);
            end = System.nanoTime();
            long wait = (long)((timePerFrame - (begin - end))/1_000_000);
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ManagerHandler.getResourceManager().unloadResources();
        System.exit(0);
    }

}
