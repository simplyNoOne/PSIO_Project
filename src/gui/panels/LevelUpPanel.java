package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LevelUpPanel extends CustomPanel  implements Interactible {

    static class LevelUpTitle extends JLabel {

        LevelUpTitle(String title) {
            super(title, SwingConstants.CENTER);

            this.setForeground(Color.yellow);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setSize(400, 30);
            this.setLocation((PANEL_WIDTH - 400) / 2, 50);
            //this.setBorder(BasicBorders.getTextFieldBorder());
        }
    }

    static class LevelUpHint extends JLabel {
        LevelUpHint(String hint) {
            super(hint, SwingConstants.CENTER);

            this.setForeground(Color.white);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
            this.setSize(400, 30);
            this.setLocation((PANEL_WIDTH - 400) / 2, 85);
            //this.setBorder(BasicBorders.getTextFieldBorder());
        }
    }

    static class LevelUpChoiceHint extends JLabel {
        LevelUpChoiceHint(String hint) {
            super(hint);

            this.setForeground(Color.lightGray);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
            this.setSize(400, LevelUpButton.BUTTON_HEIGHT + BUTTON_SPACE);
            this.setLocation(PANEL_WIDTH / 2, PANEL_HEIGHT / 2); // default
            //this.setBorder(BasicBorders.getTextFieldBorder());
        }
    }

    public static class LevelUpButton extends JButton {

        private static final int BUTTON_WIDTH = 70;
        private static final int BUTTON_HEIGHT = 70;

        LevelUpButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        LevelUpButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;
    private final static HashMap<String, LevelUpButton> levelUpButtons = new HashMap<>();
    private final static HashMap<String, LevelUpChoiceHint> levelUpChoiceHints = new HashMap<>();



    public void addButton(ImageIcon texture, String buttonKey){
        LevelUpButton levelUpButton = new LevelUpButton();
        levelUpButton.setIcon(texture);
        levelUpButton.setName(buttonKey);
        levelUpButtons.put(buttonKey, levelUpButton);
    }

    public void addButton(String text, String buttonKey) {
        LevelUpButton levelUpButton = new LevelUpButton(text);
        levelUpButton.setName(buttonKey);
        levelUpButtons.put(buttonKey, levelUpButton);
    }

    public void addChoiceHint(String hint, String buttonKey) {
        LevelUpChoiceHint choiceHint = new LevelUpChoiceHint(hint);
        levelUpChoiceHints.put(buttonKey, choiceHint);
    }

    public void update()
    {
        for(int i = 0; i < levelUpButtons.size(); i++)
        {
            int x0 = 50;
            int y0 = 40 + ((PANEL_HEIGHT - LevelUpButton.BUTTON_HEIGHT) / 2) + (i-1) * (BUTTON_SPACE + LevelUpButton.BUTTON_HEIGHT);

            levelUpButtons.get("stat" + i).setBounds(x0, y0, LevelUpButton.BUTTON_WIDTH, LevelUpButton.BUTTON_HEIGHT);
            levelUpChoiceHints.get("stat" + i).setLocation(x0 + LevelUpButton.BUTTON_WIDTH + 50, y0 - BUTTON_SPACE / 2);

            this.add(levelUpButtons.get("stat" + i));
            this.add(levelUpChoiceHints.get("stat" + i));
        }
    }


    public LevelUpPanel(){
        super();
        this.setBackground(new Color(0, 0, 0, 220));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.add(new LevelUpTitle("Level up!"));
        this.add(new LevelUpHint("Choose which path you would like to advance in."));

        update();
    }

    @Override
    public void addButtonListener(ActionListener listener, String buttonId) {
        levelUpButtons.get(buttonId).addActionListener(listener);

    }
}

