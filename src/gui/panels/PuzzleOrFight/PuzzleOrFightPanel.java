package gui.panels.PuzzleOrFight;

import gui.panels.CustomPanel;
import gui.panels.MenuPanel;
import gui.panels.PuzzleOrFight.buttons.ChoiceButton;
import interfaces.Interactible;
import managers.GUIManager;
import managers.PuzzleOrFightManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PuzzleOrFightPanel extends CustomPanel implements Interactible {

    class POFButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
    POFButton(){
        super();
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setFocusable(false);

        this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
    }
    POFButton(String text){
        this();
        this.setText(text);
        this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

    }
}

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;

    static final int BUTTON_SPACE = 30;


    Map<String,POFButton> buttons = new HashMap<>();


    public PuzzleOrFightPanel(){

        super();
        buttons.put("puzzle", new POFButton());
        buttons.put("fight", new POFButton());


        this.setBackground(Color.orange);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        buttons.get("puzzle").setIcon(ResourceManager.getTexture("puzzle"));
        buttons.get("fight").setIcon(ResourceManager.getTexture("fight"));
        buttons.get("puzzle").setBounds((PANEL_WIDTH- POFButton.BUTTON_WIDTH)/2 - POFButton.BUTTON_WIDTH -BUTTON_SPACE, (PANEL_HEIGHT- POFButton.BUTTON_HEIGHT)/2, POFButton.BUTTON_WIDTH, POFButton.BUTTON_HEIGHT);
        buttons.get("fight").setBounds((PANEL_WIDTH -  POFButton.BUTTON_WIDTH)/2 + POFButton.BUTTON_WIDTH + BUTTON_SPACE, (PANEL_HEIGHT- POFButton.BUTTON_HEIGHT)/2, POFButton.BUTTON_WIDTH, POFButton.BUTTON_HEIGHT);
        this.add(buttons.get("puzzle"));
        this.add(buttons.get("fight"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);

    }

}
