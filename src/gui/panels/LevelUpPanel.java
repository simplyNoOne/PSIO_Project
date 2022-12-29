package gui.panels;

import data.Texture;
import interfaces.Interactible;
import managers.GUIManager;
import managers.LevelUpManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LevelUpPanel extends CustomPanel  implements Interactible {




    class UpgradeStatsButton extends JButton {

        private static final int BUTTON_WIDTH = 100;
        private static final int BUTTON_HEIGHT = 100;


        UpgradeStatsButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        UpgradeStatsButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }

        public static int getButtonHeight() {
            return BUTTON_HEIGHT;
        }

        public static int getButtonWidth() {
            return BUTTON_WIDTH;
        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 10;
    private static HashMap<String, UpgradeStatsButton> statsButtons = new HashMap<>();



    public void addStatButton(ImageIcon texture, String buttonKey){
        UpgradeStatsButton statButton = new UpgradeStatsButton();
        statButton.setIcon(texture);
        statButton.setName(buttonKey);
        statsButtons.put(buttonKey, statButton);
    }

    public void update()
    {
        for(int i = 0; i < statsButtons.size(); i++)
        {
            int x0 = (PANEL_WIDTH - UpgradeStatsButton.getButtonWidth())/2;
            int y0 = (PANEL_HEIGHT - UpgradeStatsButton.getButtonHeight())/2 + (BUTTON_SPACE + UpgradeStatsButton.getButtonHeight())*(i-1);
            statsButtons.get("stat" + i).setBounds(x0, y0, UpgradeStatsButton.getButtonWidth(), UpgradeStatsButton.getButtonHeight());
            this.add(statsButtons.get("stat" + i));
        }
    }


    public LevelUpPanel(){
        super();
        this.setBackground(new Color(114, 66, 66));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        update();
    }

    @Override
    public void addButtonListener(ActionListener listener, String buttonId) {
        statsButtons.get(buttonId).addActionListener(listener);

    }
}

