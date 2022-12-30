package gui.panels;

import data.Texture;
import interfaces.Interactible;
import managers.GUIManager;
import managers.PrefightManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PrefightPanel extends CustomPanel implements Interactible {


    class PrefightButton extends JButton {

        boolean selected = false;
        PrefightButton(){
            super();

            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setBorder(new LineBorder(Color.black, 2));
            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }

        public void buttonDeselected(){
            this.setBorder(new LineBorder(Color.black, 2));
            selected = false;
        }

        public boolean getSelected(){return selected;}
    }
    class ConfirmButton extends PrefightButton{

        static final int BUTTON_WIDTH = 250;
        static final int BUTTON_HEIGHT = 50;
        ConfirmButton(){

            this.setBorder(null);
            this.setText("start fight");
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            this.setBackground(new Color(5, 5, 5));
            this.setForeground(new Color(250, 250, 250));
            int x0 = (PANEL_WIDTH - BUTTON_WIDTH)/2;
            int y0 = 400;

            this.setBounds(x0, y0, BUTTON_WIDTH, BUTTON_HEIGHT);
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

        public void buttonSelected(){

            collectibleButtons.forEach((key, value) -> {
                value.setBorder(new LineBorder(Color.black, 2));
                value.selected = false;
            });
            this.setBorder(new LineBorder(Color.white, 2));
            selected = true;
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

        public void buttonSelected(){
            weaponButtons.forEach((key, value) -> {
                value.setBorder(new LineBorder(Color.black, 2));
                value.selected = false;
            });
            this.setBorder(new LineBorder(Color.white, 2));
            selected  = true;

        }
    }


    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;



    final private ConfirmButton confirmButton;
    private final Map<String, WeaponButton> weaponButtons = new HashMap<>();
    private final Map<String, CollectibleButton> collectibleButtons = new HashMap<>();
    private final Map<String, ArrayList<Texture>> weaponTextures = new HashMap<>();
    private final Map<String, ArrayList<Texture>> collectibleTextures = new HashMap<>();



    public PrefightPanel(){
        super();
        confirmButton = new ConfirmButton();
        this.setBackground(new Color(0, 0, 0, 220));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(confirmButton);

        ArrayList<Texture> temp = new ArrayList<>();

        JLabel title = new JLabel("PREPARE FOR A FIGHT!");
        JLabel hint = new JLabel("Choose you weapon and a power-up.");

        title.setForeground(Color.white);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        title.setSize(300, 30);
        title.setLocation(PANEL_WIDTH/ 2 - 140, 50);

        hint.setForeground(Color.white);
        hint.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        hint.setSize(300, 50);
        hint.setLocation(PANEL_WIDTH/ 2 - 150, 85);

        this.add(title);
        this.add(hint);

        weaponButtons.put("axe", new WeaponButton());
        weaponButtons.put("dagger", new WeaponButton());
        weaponButtons.put("sword", new WeaponButton());

        collectibleButtons.put("collectible1", new CollectibleButton());
        collectibleButtons.put("collectible2", new CollectibleButton());
        collectibleButtons.put("collectible3", new CollectibleButton());
        collectibleButtons.put("collectible4", new CollectibleButton());
        collectibleButtons.put("collectible5", new CollectibleButton());


        temp.add( ResourceManager.getTexture("grayAxe"));
        temp.add( ResourceManager.getTexture("axe"));
        weaponTextures.put("axe", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("graySword"));
        temp.add( ResourceManager.getTexture("sword"));
        weaponTextures.put("sword", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("grayDagger"));
        temp.add( ResourceManager.getTexture("dagger"));
        weaponTextures.put("dagger", temp );
        temp = new ArrayList<>();

        weaponButtons.get("axe").setName("axe");
        weaponButtons.get("dagger").setName("dagger");
        weaponButtons.get("sword").setName("sword");

        weaponButtons.get("axe").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE)*(-1), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 + 50);
        weaponButtons.get("dagger").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2, (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 + 50);
        weaponButtons.get("sword").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 + 50);

        weaponButtons.forEach((key, value) -> this.add(value));


        temp.add( ResourceManager.getTexture("grayCol1"));
        temp.add( ResourceManager.getTexture("col1"));
        collectibleTextures.put("collectible1", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("grayCol2"));
        temp.add( ResourceManager.getTexture("col2"));
        collectibleTextures.put("collectible2", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("grayCol3"));
        temp.add( ResourceManager.getTexture("col3"));
        collectibleTextures.put("collectible3", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("grayCol4"));
        temp.add( ResourceManager.getTexture("col5"));
        collectibleTextures.put("collectible4", temp );
        temp = new ArrayList<>();

        temp.add( ResourceManager.getTexture("grayCol5"));
        temp.add( ResourceManager.getTexture("col5"));
        collectibleTextures.put("collectible5", temp );

        collectibleButtons.get("collectible1").setName("collectible1");
        collectibleButtons.get("collectible2").setName("collectible2");
        collectibleButtons.get("collectible3").setName("collectible3");
        collectibleButtons.get("collectible4").setName("collectible4");
        collectibleButtons.get("collectible5").setName("collectible5");

        collectibleButtons.get("collectible1").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE)*(-2), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50);
        collectibleButtons.get("collectible2").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE)*(-1), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50);
        collectibleButtons.get("collectible3").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2, (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50);
        collectibleButtons.get("collectible4").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50);
        collectibleButtons.get("collectible5").setLocation((PANEL_WIDTH - WeaponButton.getButtonWidth())/2 + (WeaponButton.getButtonWidth() + BUTTON_SPACE)*(2), (PANEL_HEIGHT - WeaponButton.getButtonHeight())/2 - WeaponButton.getButtonHeight() - BUTTON_SPACE + 50);
        collectibleButtons.forEach((key, value) -> this.add(value));



    }



    public void refreshButtons(){
        weaponButtons.forEach((key, value) ->{
            value.buttonDeselected();
            value.setIcon(weaponTextures.get(key).get(0));
            value.setBackground(new Color(120, 120, 120));
            if(value.getActionListeners().length > 0)
                value.removeActionListener(value.getActionListeners()[0]);
        });
        collectibleButtons.forEach((key, value) -> {
            value.buttonDeselected();
            value.setIcon(collectibleTextures.get(key).get(0));
            if(value.getActionListeners().length > 0)
                value.removeActionListener(value.getActionListeners()[0]);
        });

        for( String name : PrefightManager.getActiveWeapons()) {
            weaponButtons.get(name).setBackground(new Color(220, 220, 220));
            weaponButtons.get(name).setIcon(weaponTextures.get(name).get(1));
        }
        for( String name : PrefightManager.getActiveCollectibles())
            collectibleButtons.get(name).setIcon(collectibleTextures.get(name).get(1));
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
