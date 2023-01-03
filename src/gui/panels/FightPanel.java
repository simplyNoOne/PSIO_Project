
package gui.panels;

import gui.panels.CustomPanel;
import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FightPanel extends CustomPanel implements Interactible {


    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;

    private JLabel message;

    public FightPanel(){

        super();
        message = new JLabel("", SwingConstants.CENTER);
        message.setBounds((PANEL_WIDTH - 500)/2, 120, 500, 50);
        message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        message.setForeground(Color.white);
        this.add(message);

        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);



    }

    public void addButtonListener(ActionListener listener, String buttonId){

    }

    public void setMessage(String message){this.message.setText(message);}

}

