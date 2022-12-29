package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PuzzlePanel extends CustomPanel implements Interactible {
    class PuzzleButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
        PuzzleButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        PuzzleButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;

    private Map<String, PuzzleButton> buttons = new HashMap<>();
    public PuzzlePanel(){

        super();
        buttons.put("correct", new PuzzleButton());
        buttons.put("wrong", new PuzzleButton());


        this.setBackground(new Color(0, 0, 0));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        buttons.get("correct").setIcon(ResourceManager.getTexture("correct"));
        buttons.get("wrong").setIcon(ResourceManager.getTexture("wrong"));
        int correctButtonX = (PANEL_WIDTH- PuzzleButton.BUTTON_WIDTH)/2 - PuzzleButton.BUTTON_WIDTH -BUTTON_SPACE;
        int correctButtonY = (PANEL_HEIGHT- PuzzleButton.BUTTON_HEIGHT)/2;
        buttons.get("correct").setBounds(correctButtonX, correctButtonY, PuzzleButton.BUTTON_WIDTH, PuzzleButton.BUTTON_HEIGHT);
        int wrongButtonX = (PANEL_WIDTH -  PuzzleButton.BUTTON_WIDTH)/2 + PuzzleButton.BUTTON_WIDTH + BUTTON_SPACE;
        int wrongButtonY =  (PANEL_HEIGHT- PuzzleButton.BUTTON_HEIGHT)/2;
        buttons.get("wrong").setBounds(wrongButtonX, wrongButtonY, PuzzleButton.BUTTON_WIDTH, PuzzleButton.BUTTON_HEIGHT);
        this.add(buttons.get("correct"));
        this.add(buttons.get("wrong"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);
    }

}
