package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScoresPanel extends CustomPanel implements Interactible {

    static class ScoresButton extends JButton {
        static final int BUTTON_WIDTH = 170;
        static final int BUTTON_HEIGHT = 40;
        static final int POS_X = (PANEL_WIDTH - ScoresButton.BUTTON_WIDTH) / 2;
        static final int POS_Y = PANEL_HEIGHT - POS_X;

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

    public class ScoreLabel extends JLabel {
        static final int WIDTH = (int) (0.8f * ScoresPanel.PANEL_WIDTH);
        static final int HEIGHT = calcHeight();
        ScoreLabel() {
            this.setBackground(Color.white);
            this.setForeground(Color.black);
            this.setSize(WIDTH, HEIGHT);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
            // TODO continue here, need to add labels to the panel
        }
        static int calcHeight() {
            int verticalWorkingSpace = ScoresPanel.PANEL_HEIGHT - ScoresButton.POS_Y;
            return (ScoresPanel.HEIGHT - verticalWorkingSpace) / MAX_SCORES_ON_PANEL;
        }
    }

    static int PANEL_WIDTH = 400;
    private static int PANEL_HEIGHT = 500;
    private static final int MAX_SCORES_ON_PANEL = 6;

    ScoresButton backButton = new ScoresButton("Back");

    public ScoresPanel(){
        super();
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        //this.setPreferredSize(new Dimension(600, 600));

        backButton.setBounds(ScoresButton.POS_X, ScoresButton.POS_Y, ScoresButton.BUTTON_WIDTH, ScoresButton.BUTTON_HEIGHT);

        this.add(backButton);
        this.setBackground(Color.red);
        this.setVisible(true);
    }

    public void addButtonListener(ActionListener listener, String buttonId){
        backButton.addActionListener(listener);
    }
}
