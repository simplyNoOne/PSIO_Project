package gui.panels;

import main.StateMachine;
import managers.GUIManager;

import java.awt.*;
import java.awt.event.ActionListener;

public class BackgroundPanel extends CustomPanel{

    public BackgroundPanel(){
        this.setBackground(Color.green);
        this.setBounds(0,0, GUIManager.getWidth(), GUIManager.getHeight());
    }


    public void scroll(double dT){
        if(this.getX()< - GUIManager.getWidth())
            StateMachine.nextState();
        else
            this.setLocation(this.getX() - (int)(dT * 200), this.getY());
    }
}
