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

public class PuzzleOrFightPanel extends CustomPanel implements Interactible {

    class PuzzleOrFightButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
        PuzzleOrFightButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        PuzzleOrFightButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;


    private Map<String,PuzzleOrFightButton> buttons = new HashMap<>();


    public PuzzleOrFightPanel(){

        super();
        buttons.put("puzzle", new PuzzleOrFightButton());
        buttons.put("fight", new PuzzleOrFightButton());


        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        buttons.get("puzzle").setIcon(ManagerHandler.getResourceManager().getTexture("puzzle"));
        buttons.get("fight").setIcon(ManagerHandler.getResourceManager().getTexture("fight"));
        int puzzleButtonX = (PANEL_WIDTH- PuzzleOrFightButton.BUTTON_WIDTH)/2 - PuzzleOrFightButton.BUTTON_WIDTH -BUTTON_SPACE;
        int puzzleButtonY = (PANEL_HEIGHT- PuzzleOrFightButton.BUTTON_HEIGHT)/2;
        buttons.get("puzzle").setBounds(puzzleButtonX, puzzleButtonY, PuzzleOrFightButton.BUTTON_WIDTH, PuzzleOrFightButton.BUTTON_HEIGHT);
        int fightButtonX = (PANEL_WIDTH -  PuzzleOrFightButton.BUTTON_WIDTH)/2 + PuzzleOrFightButton.BUTTON_WIDTH + BUTTON_SPACE;
        int fightButtonY =  (PANEL_HEIGHT- PuzzleOrFightButton.BUTTON_HEIGHT)/2;
        buttons.get("fight").setBounds(fightButtonX, fightButtonY, PuzzleOrFightButton.BUTTON_WIDTH, PuzzleOrFightButton.BUTTON_HEIGHT);
        this.add(buttons.get("puzzle"));
        this.add(buttons.get("fight"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);
    }

}
