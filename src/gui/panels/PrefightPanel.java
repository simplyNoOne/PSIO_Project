package gui.panels;

import data.Texture;
import interfaces.Interactible;
import managers.GUIManager;
import managers.PrefightManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class PrefightPanel extends CustomPanel implements Interactible {


    class PrefightButton extends JButton {
        PrefightButton(){
            super();

            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        PrefightButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }
    class ConfirmButton extends PrefightButton{

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
        ConfirmButton(){

            Texture texture = ResourceManager.getTexture("confirm2");
            this.setIcon(texture);
            int x0 = (PANEL_WIDTH - texture.getIconWidth())/2;
            int y0 = (PANEL_HEIGHT + CollectibleButton.BUTTON_HEIGHT)/2 + BUTTON_SPACE + 50;

            this.setBounds(x0, y0, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
        }
    }
    public  class CollectibleButton extends PrefightButton{
        private static final int BUTTON_WIDTH = 64;
        private static final int BUTTON_HEIGHT = 64;
        CollectibleButton(){
            super();
            this.setSize(CollectibleButton.BUTTON_WIDTH, CollectibleButton.BUTTON_HEIGHT);
        }

        public static int getButtonHeight() {
            return BUTTON_HEIGHT;
        }

        public static int getButtonWidth() {
            return BUTTON_WIDTH;
        }
    }

    public class WeaponButton extends PrefightButton{
        private static final int BUTTON_WIDTH = 64;
        private static final int BUTTON_HEIGHT = 64;
        WeaponButton(){
            super();
            this.setSize(WeaponButton.BUTTON_WIDTH, WeaponButton.BUTTON_HEIGHT);
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
    private final static int BUTTON_SPACE = 30;



    private ConfirmButton confirmButton;
    private Map<String, WeaponButton> weaponButtons = new HashMap<>();
    private Map<String, CollectibleButton> collectibleButtons = new HashMap<>();



    public PrefightPanel(){
        super();
        confirmButton = new ConfirmButton();
        this.setBackground(Color.cyan);
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(confirmButton);

    }

    public void addWeaponButton(ImageIcon texture, String buttonKey, String weaponName){
        WeaponButton wButton = new WeaponButton();
        wButton.setIcon(texture);
        wButton.setName(weaponName);
        weaponButtons.put(buttonKey, wButton);
    }

    public void addCollectibleButton(ImageIcon texture, String buttonKey, String collectibleName){
        CollectibleButton cButton = new CollectibleButton();
        cButton.setIcon(texture);
        cButton.setName(collectibleName);
        collectibleButtons.put(buttonKey, cButton);
    }

    public void updatePanel(){

        for(int i = 0; i<collectibleButtons.size(); i++){
            int x0 = (PANEL_WIDTH - CollectibleButton.getButtonWidth())/2 + (CollectibleButton.getButtonWidth() + BUTTON_SPACE)*(i-1);
            int y0 = (PANEL_HEIGHT - CollectibleButton.getButtonHeight())/2 + 50;
            collectibleButtons.get("collectible" + (i + 1)).setLocation(x0, y0);
            this.add(collectibleButtons.get("collectible" + (i + 1)));
        }
        for(int i = 0; i<weaponButtons.size(); i++){
            int x0 = (PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE)*(i-1);
            int y0 = (PANEL_HEIGHT - CollectibleButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50;
            weaponButtons.get("weapon" + (i + 1)).setLocation(x0, y0);
            this.add(weaponButtons.get("weapon" + (i + 1)));
        }
    }



    public void addButtonListener(ActionListener listener, String buttonId){
        if (listener instanceof PrefightManager.CollectibleButtonListener)
            collectibleButtons.get(buttonId).addActionListener(listener);
        else if (listener instanceof PrefightManager.WeaponButtonListener)
            weaponButtons.get(buttonId).addActionListener(listener);
        else
            confirmButton.addActionListener(listener);

    }

}
