package managers;

import com.sun.tools.javac.Main;
import data.Collectible;
import data.Texture;
import data.Weapon;
import gui.panels.Prefight.buttons.CollectibleButton;
import gui.panels.Prefight.buttons.ConfirmButton;
import gui.panels.Prefight.buttons.WeaponButton;
import main.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrefightManager {

    private static final WeaponButtonListener weaponButtonListener = new WeaponButtonListener();
    private static final CollectibleButtonListener collectibleButtonListener = new CollectibleButtonListener();
    private static final ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();

    private static final int HORIZONTAL_BUTTON_SPACING = 50;
    private static final int VERTICAL_BUTTON_SPACING = 50;
    private static final int TITLE_BAR_HEIGHT = 31;
    public static class WeaponButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String weaponName = ((WeaponButton) e.getSource()).getWeaponName();
            System.out.println(weaponName + " has been clicked!");
//            MainApp.getPlayer().getInventory().setActiveWeapon(tag);
        }
    }

    public static class CollectibleButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String collectibleName = ((CollectibleButton) e.getSource()).getCollectibleName();
            System.out.println(collectibleName + " has been clicked!");
//            MainApp.getPlayer().getInventory().setActiveCollectible(tag);
        }
    }

    public static class ConfirmButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Confirm button has been clicked!");
            StateMachine.nextState();
        }
    }


    public static ConfirmButtonListener getConfirmButtonListener() {
        return confirmButtonListener;
    }

    public static WeaponButtonListener getWeaponButtonListener() {
        return weaponButtonListener;
    }

    public static CollectibleButtonListener getCollectibleButtonListener() {
        return collectibleButtonListener;
    }


    public static ArrayList<WeaponButton> getAllWeaponButtons() {
        ArrayList<Weapon> availableWeapons = MainApp.getPlayer().getInventory().getWeapons();
        ArrayList<WeaponButton> weaponButtons = new ArrayList<>();
        for (int id = 0; id < availableWeapons.size(); id++) {
            Weapon weapon = availableWeapons.get(id);
            Texture weaponTexture = new Texture(ResourceManager.getTexture(weapon.getName()).getTexturePath());
            int x0 = (GUIManager.getWidth() - availableWeapons.size() * weaponTexture.getIconWidth() - (availableWeapons.size()-1)*HORIZONTAL_BUTTON_SPACING)/2 + id*(weaponTexture.getIconWidth() + HORIZONTAL_BUTTON_SPACING);
            int y0 = (GUIManager.getHeight() - VERTICAL_BUTTON_SPACING - TITLE_BAR_HEIGHT)/2 - weaponTexture.getIconHeight();
            WeaponButton weaponButton = new WeaponButton(weapon.getName(), weaponTexture);
            weaponButton.setBounds(x0, y0, weaponTexture.getIconWidth(), weaponTexture.getIconHeight());
            weaponButton.addActionListener(weaponButtonListener);
            weaponButtons.add(weaponButton);
        }
        return weaponButtons;
    }

    public static ArrayList<CollectibleButton> getAllCollectibleButtons() {
        ArrayList<Collectible> availableCollectibles = MainApp.getPlayer().getInventory().getCollectibles();
        ArrayList<CollectibleButton> collectibleButtons = new ArrayList<>();
        for (int id = 0; id < availableCollectibles.size(); id++) {
            Collectible collectible = availableCollectibles.get(id);
            Texture collectibleTexture = ResourceManager.getTexture(collectible.getName());
            int x0 = (GUIManager.getWidth() - availableCollectibles.size() * collectibleTexture.getIconWidth() - (availableCollectibles.size()-1)*HORIZONTAL_BUTTON_SPACING)/2 + id*(collectibleTexture.getIconWidth() + HORIZONTAL_BUTTON_SPACING);
            int y0 = (GUIManager.getHeight() + VERTICAL_BUTTON_SPACING - TITLE_BAR_HEIGHT)/2;

            CollectibleButton collectibleButton = new CollectibleButton(collectible.getName(), collectibleTexture);
            collectibleButton.setBounds(x0, y0, collectibleTexture.getIconWidth(), collectibleTexture.getIconHeight());
            collectibleButton.addActionListener(collectibleButtonListener);
            collectibleButtons.add(collectibleButton);
        }
        return collectibleButtons;
    }

    public static ConfirmButton getConfirmButton()
    {
        Texture confirmTexture = new Texture(ResourceManager.getTexture("confirm2").getTexturePath());
        int x0 = (GUIManager.getWidth() - confirmTexture.getIconWidth())/2;
        int y0 = GUIManager.getHeight() - TITLE_BAR_HEIGHT - confirmTexture.getIconHeight() - VERTICAL_BUTTON_SPACING;
        ConfirmButton confirmButton = new ConfirmButton(confirmTexture);
        confirmButton.setBounds(x0, y0, confirmTexture.getIconWidth(), confirmTexture.getIconHeight());
        confirmButton.addActionListener(confirmButtonListener);


        return confirmButton;
    }
}
