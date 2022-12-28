package gui.panels.Prefight;

import data.Texture;
import gui.panels.CustomPanel;
import gui.panels.Prefight.buttons.CollectibleButton;
import gui.panels.Prefight.buttons.ConfirmButton;
import gui.panels.Prefight.buttons.WeaponButton;
import gui.panels.PuzzleOrFight.PuzzleOrFightPanel;
import interfaces.Interactible;
import managers.GUIManager;
import managers.PrefightManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PrefightPanel extends CustomPanel implements Interactible {
    static final int W_BUTTON_WIDTH = 64;
    static final int W_BUTTON_HEIGHT = 64;
    static final int C_BUTTON_WIDTH = 64;
    static final int C_BUTTON_HEIGHT = 64;

     class PFButton extends JButton {
        public String name;

        PFButton(){
            super();

            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        PFButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }
    class ConfButton extends PFButton{
         ConfButton(){

            this.setIcon(ResourceManager.getTexture("confirm2"));

             this.setBounds(300, 0, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
         }
    }
    public  class CPFButton extends PFButton{
         CPFButton(){
             super();
             this.setSize(C_BUTTON_WIDTH, C_BUTTON_HEIGHT);
         }
    }

    public class WPFButton extends PFButton{
        WPFButton(){
            super();
            this.setSize(W_BUTTON_WIDTH, W_BUTTON_HEIGHT);
        }
    }

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;

    ConfButton confButton;
    Map<String, PFButton> wButtons = new HashMap<>();
    Map<String, PFButton> cButtons = new HashMap<>();

    public PrefightPanel(){
        super();
        confButton = new ConfButton();
        this.setBackground(Color.cyan);
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(confButton);

    }

    public void addButtonListener(ActionListener listener, String buttonId){
        if (listener instanceof PrefightManager.CollectibleButtonListener)
            cButtons.get(buttonId).addActionListener(listener);
        else if (listener instanceof PrefightManager.WeaponButtonListener)
            wButtons.get(buttonId).addActionListener(listener);
        else
            confButton.addActionListener(listener);

    }

    public void addWeaponButton(ImageIcon txt, String num, String name){
        WPFButton wButton = new WPFButton();
        wButton.setIcon(txt);
        wButton.name = name;
        wButtons.put(num, wButton);
    }

    public void addCollectibleButton(ImageIcon txt, String num, String name){
        CPFButton cButton = new CPFButton();
        cButton.setIcon(txt);
        cButton.name = name;
        cButtons.put(num, cButton);
    }

    public void updatePanel(){

        for(int i = 0; i<cButtons.size(); i++){
            cButtons.get("collectible" + (i + 1)).setLocation( 120*i, 300);
            this.add(cButtons.get("collectible" + (i + 1)));
        }
        for(int i = 0; i<wButtons.size(); i++){
            wButtons.get("weapon" + (i + 1)).setLocation( 450*i, 300);
            this.add(wButtons.get("weapon" + (i + 1)));
        }
    }
}
