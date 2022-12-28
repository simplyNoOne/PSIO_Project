package gui.panels;

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
    static final int W_BUTTON_WIDTH = 64;
    static final int W_BUTTON_HEIGHT = 64;
    static final int C_BUTTON_WIDTH = 100;
    static final int C_BUTTON_HEIGHT = 100;

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
         ConfirmButton(){

            this.setIcon(ResourceManager.getTexture("confirm2"));

            this.setBounds(300, 0, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
         }
    }
    public  class CollectibleButton extends PrefightButton{
         CollectibleButton(){
             super();
             this.setSize(C_BUTTON_WIDTH, C_BUTTON_HEIGHT);
         }
    }

    public class WeaponButton extends PrefightButton{
        WeaponButton(){
            super();
            this.setSize(W_BUTTON_WIDTH, W_BUTTON_HEIGHT);
        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;

    ConfirmButton confirmButton;
    Map<String, WeaponButton> weaponButtons = new HashMap<>();
    Map<String, CollectibleButton> collectibleButtons = new HashMap<>();



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
            collectibleButtons.get("collectible" + (i + 1)).setLocation( 120*i, 300);
            this.add(collectibleButtons.get("collectible" + (i + 1)));
        }
        for(int i = 0; i<weaponButtons.size(); i++){
            weaponButtons.get("weapon" + (i + 1)).setLocation( 450*i, 300);
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
