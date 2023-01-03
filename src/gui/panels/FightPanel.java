
package gui.panels;

import gui.panels.CustomPanel;
import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FightPanel extends CustomPanel implements Interactible {
    class FightButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
        FightButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        FightButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;

    private JLabel message;

    //TODO get rid of this shit
    private Map<String, FightButton> buttons = new HashMap<>();
    public FightPanel(){

        super();
        message = new JLabel("", SwingConstants.CENTER);
        message.setBounds((PANEL_WIDTH - 200)/2, 200, 400, 50);
        message.setFont(new Font(Font.SANS_SERIF, 1, 25));
        message.setOpaque(true);
        message.setBackground(Color.white);
        this.add(message);

        buttons.put("won", new FightButton());
        buttons.put("lost", new FightButton());
        buttons.put("levelup", new FightButton());


        this.setBackground(new Color(72, 2, 194));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        buttons.get("won").setIcon(ResourceManager.getTexture("won"));
        buttons.get("lost").setIcon(ResourceManager.getTexture("lost"));
        buttons.get("levelup").setIcon(ResourceManager.getTexture("levelup"));

        int wonButtonX = (PANEL_WIDTH - FightButton.BUTTON_WIDTH)/2 - FightButton.BUTTON_WIDTH - BUTTON_SPACE;
        int wonButtonY = (PANEL_HEIGHT - FightButton.BUTTON_HEIGHT)/2;
        buttons.get("won").setBounds(wonButtonX, wonButtonY, FightButton.BUTTON_WIDTH, FightButton.BUTTON_HEIGHT);

        int lostButtonX = (PANEL_WIDTH - FightButton.BUTTON_WIDTH)/2;
        int lostButtonY = (PANEL_HEIGHT - FightButton.BUTTON_HEIGHT)/2;
        buttons.get("lost").setBounds(lostButtonX, lostButtonY, FightButton.BUTTON_WIDTH, FightButton.BUTTON_HEIGHT);

        int levelupButtonX = (PANEL_WIDTH - FightButton.BUTTON_WIDTH)/2 + FightButton.BUTTON_WIDTH + BUTTON_SPACE;
        int levelupButtonY = (PANEL_HEIGHT - FightButton.BUTTON_HEIGHT)/2;
        buttons.get("levelup").setBounds(levelupButtonX, levelupButtonY, FightButton.BUTTON_WIDTH, FightButton.BUTTON_HEIGHT);

        this.add(buttons.get("won"));
        this.add(buttons.get("lost"));
        this.add(buttons.get("levelup"));

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        buttons.get(buttonId).addActionListener(listener);
    }

    public void setMessage(String message){this.message.setText(message);}

}

