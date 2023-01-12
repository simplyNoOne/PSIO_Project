package gui.panels;

import gui.CustomButton;
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

    static class PuzzleOrFightButton extends CustomButton {

        static final int BUTTON_WIDTH = 200;
        static final int BUTTON_HEIGHT = 70;
        PuzzleOrFightButton(){
            super();
            this.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        PuzzleOrFightButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 40 ));

        }
    }

    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;
    private final static int BUTTON_SPACE = 25;


    private Map<String,PuzzleOrFightButton> buttons = new HashMap<>();


    public PuzzleOrFightPanel(){

        super();
        buttons.put("puzzle", new PuzzleOrFightButton());
        buttons.put("fight", new PuzzleOrFightButton());


        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);

        buttons.get("puzzle").setText("PUZZLE");
        buttons.get("fight").setText("FIGHT");
        //buttons.get("puzzle").setIcon(ManagerHandler.getResourceManager().getTexture("puzzle"));
        //buttons.get("fight").setIcon(ManagerHandler.getResourceManager().getTexture("fight"));
        int puzzleButtonX = (PANEL_WIDTH- PuzzleOrFightButton.BUTTON_WIDTH)/2 - PuzzleOrFightButton.BUTTON_WIDTH/2 - BUTTON_SPACE;
        int puzzleButtonY = (PANEL_HEIGHT- PuzzleOrFightButton.BUTTON_HEIGHT)/2;
        buttons.get("puzzle").setLocation(puzzleButtonX, puzzleButtonY);
        int fightButtonX = (PANEL_WIDTH -  PuzzleOrFightButton.BUTTON_WIDTH)/2 + PuzzleOrFightButton.BUTTON_WIDTH/2 + BUTTON_SPACE;
        int fightButtonY =  (PANEL_HEIGHT- PuzzleOrFightButton.BUTTON_HEIGHT)/2;
        buttons.get("fight").setLocation(fightButtonX, fightButtonY);
        this.add(buttons.get("puzzle"));
        this.add(buttons.get("fight"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);
    }

}
