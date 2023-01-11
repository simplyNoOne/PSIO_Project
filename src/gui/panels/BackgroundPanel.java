package gui.panels;

import main.ManagerHandler;
import main.StateMachine;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BackgroundPanel extends CustomPanel{

    private final static int SPEED = 200;

    JLabel currentBg;
    JLabel prevBg;

    public BackgroundPanel(){

        currentBg = new JLabel(ManagerHandler.getResourceManager().getTexture("background"));
        prevBg = new JLabel(ManagerHandler.getResourceManager().getTexture("background"));
        prevBg.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
        currentBg.setBounds(GUIManager.getWidth(), 0, GUIManager.getWidth(), GUIManager.getHeight());

        this.add(currentBg);
        this.add(prevBg);
        this.setBounds(0,0, GUIManager.getWidth(), GUIManager.getHeight());
    }


    public void scroll(double dT){
        if(prevBg.getX()< - GUIManager.getWidth()) {
            StateMachine.nextState();
            prevBg.setLocation(0, 0);
            currentBg.setLocation(GUIManager.getWidth(), 0);
        }
        else {
            currentBg.setLocation(currentBg.getX() - (int) (dT * SPEED), 0);
            prevBg.setLocation(prevBg.getX() - (int) (dT * SPEED), 0);
        }
    }

    public static int getSpeed(){return SPEED;}
}
