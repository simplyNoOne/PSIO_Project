package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuPanel extends CustomPanel implements Interactible {

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


    Map<String, MenuButton> buttons = new HashMap<>();
    MenuButton startButton = new MenuButton("Start Game");
    MenuButton quitButton = new MenuButton("Quit Game");
    MenuButton scoresButton = new MenuButton("See Scores");
    public MenuPanel(){
        super();

        buttons.put("start", new MenuButton("Start Game"));
        buttons.put("quit", new MenuButton("Quit Game"));
        buttons.put("scores", new MenuButton("See Scores"));

        this.setBounds(0, 0, GUIManager.getWidth(), GUIManager.getHeight());
        this.setBackground(Color.blue);
        this.setLayout(null);
        buttons.get("start").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 - MenuButton.BUTTON_WIDTH -BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("scores").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2,MenuButton. BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        buttons.get("quit").setBounds((GUIManager.getWidth()-MenuButton.BUTTON_WIDTH)/2 + MenuButton.BUTTON_WIDTH + BUTTON_SPACE, (GUIManager.getHeight()-MenuButton.BUTTON_HEIGHT)/2, MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        this.add(buttons.get("start"));
        this.add(buttons.get("scores"));
        this.add(buttons.get("quit"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);


    }
}
