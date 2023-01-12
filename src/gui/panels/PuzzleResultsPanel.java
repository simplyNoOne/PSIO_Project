package gui.panels;

import interfaces.Interactible;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PuzzleResultsPanel extends CustomPanel implements Interactible {

    class OkButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;

        OkButton() {
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;

    private OkButton okButton;
    public PuzzleResultsPanel(){

        super();
        okButton = new OkButton();


        this.setBackground(new Color(0, 150, 0));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.setLayout(null);

        okButton.setIcon(ManagerHandler.getResourceManager().getTexture("ok"));
        int okButtonX = (PANEL_WIDTH- OkButton.BUTTON_WIDTH)/2;
        int okButtonY = (PANEL_HEIGHT- OkButton.BUTTON_HEIGHT)/2;
        okButton.setBounds(okButtonX, okButtonY, OkButton.BUTTON_WIDTH, OkButton.BUTTON_HEIGHT);
        this.add(okButton);


    }

    public void addButtonListener(ActionListener listener, String buttonId){
        okButton.addActionListener(listener);
    }

}