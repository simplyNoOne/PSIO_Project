package gui.panels;

import main.StateMachine;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BackgroundPanel extends CustomPanel{

    JLabel currentBg;
    JLabel prevBg;

    public BackgroundPanel(){

        currentBg = new JLabel(ResourceManager.getTexture("background"));
        prevBg = new JLabel(ResourceManager.getTexture("background"));
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
            currentBg.setLocation(currentBg.getX() - (int) (dT * 200), 0);
            prevBg.setLocation(prevBg.getX() - (int) (dT * 200), 0);
        }
        }
}
