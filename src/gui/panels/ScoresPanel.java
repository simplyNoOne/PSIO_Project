package gui.panels;

import gui.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class ScoresPanel extends CustomPanel {

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

    ScoresButton backButton = new ScoresButton("Back");

    public ScoresPanel(){
        super();
        this.setPreferredSize(new Dimension(600, 600));
        backButton.setBounds((GUIManager.getWidth() - ScoresButton.BUTTON_WIDTH) / 2, (GUIManager.getHeight() - ScoresButton.BUTTON_HEIGHT) / 2, ScoresButton.BUTTON_WIDTH, ScoresButton.BUTTON_HEIGHT);
        this.add(backButton);
        this.setVisible(true);
    }
}
