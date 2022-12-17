package gui.panels;

import gui.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class MenuPanel extends CustomPanel{

    class MenuButton extends JButton{
        static final int BUTTON_WIDTH = 200;
        static final int BUTTON_HEIGHT = 60;

        MenuButton(){
            super();
            this.setFocusPainted(false);
        }
        MenuButton(String text){
            this();
            this.setText(text);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
            this.setFocusPainted(false);

        }
    }

    static final int BUTTON_SPACE = 30;

    MenuButton startButton = new MenuButton("Start Game");
    MenuButton quitButton = new MenuButton("Quit Game");
    MenuButton scoresButton = new MenuButton("See Scores");
    public MenuPanel(){
        super();
        startButton.setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 - MenuButton.BUTTON_WIDTH -BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        scoresButton.setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2,MenuButton. BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        quitButton.setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 + MenuButton.BUTTON_WIDTH + BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        this.add(startButton);
        this.add(scoresButton);
        this.add(quitButton);

    }

}
