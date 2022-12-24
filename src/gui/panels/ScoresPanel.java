package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScoresPanel extends CustomPanel implements Interactible {

    static class ScoresButton extends JButton {
        static final int BUTTON_WIDTH = 170;
        static final int BUTTON_HEIGHT = 40;

        ScoresButton() {
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setFont(new Font("SansSerif", Font.BOLD, 30));
            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
            this.setFocusPainted(false);
        }

        ScoresButton(String text) {
            this();
            this.setText(text);
        }
    }

    static int PANEL_WIDTH = 400;
    private static int PANEL_HEIGHT = 500;

    ScoresButton backButton = new ScoresButton("Back");

    public ScoresPanel(){
        super();
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        //this.setPreferredSize(new Dimension(600, 600));
        backButton.setBounds((PANEL_WIDTH - ScoresButton.BUTTON_WIDTH) / 2, (PANEL_HEIGHT - ScoresButton.BUTTON_HEIGHT) / 2, ScoresButton.BUTTON_WIDTH, ScoresButton.BUTTON_HEIGHT);
        this.add(backButton);
        this.setBackground(Color.red);
        this.setVisible(true);
    }

    public void addButtonListener(ActionListener listener, String buttonId){
        backButton.addActionListener(listener);
    }
}
