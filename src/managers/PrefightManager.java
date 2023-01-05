package managers;

import data.Collectible;
import data.Texture;
import data.Weapon;
import gui.panels.PrefightPanel;
import interfaces.Interactible;
import main.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrefightManager {



    public static class WeaponButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String weaponName = ((PrefightPanel.WeaponButton) e.getSource()).getName();
            if(((PrefightPanel.WeaponButton) e.getSource()).getSelected()) {
                ((PrefightPanel.WeaponButton) e.getSource()).buttonDeselected();
                MainApp.getPlayer().resetActiveWeapon();
            }
            else {
                ((PrefightPanel.WeaponButton) e.getSource()).buttonSelected();
                System.out.println(weaponName + " has been clicked!");
                MainApp.getPlayer().setActiveWeapon(weaponName);
            }
        }
    }
    public static class CollectibleButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String collectibleName = ((PrefightPanel.CollectibleButton) e.getSource()).getName();
            if(((PrefightPanel.CollectibleButton) e.getSource()).getSelected()) {
                ((PrefightPanel.CollectibleButton) e.getSource()).buttonDeselected();
                MainApp.getPlayer().resetActiveCollectible();
            }
            else {
                ((PrefightPanel.CollectibleButton) e.getSource()).buttonSelected();
                System.out.println(collectibleName + " has been clicked!");
                MainApp.getPlayer().setActiveCollectible(collectibleName);
            }
//
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

    private static final WeaponButtonListener weaponButtonListener = new WeaponButtonListener();
    private static final CollectibleButtonListener collectibleButtonListener = new CollectibleButtonListener();
    private static final ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();


    public static ArrayList<String> getActiveCollectibles() {
        ArrayList<Collectible> availableCollectibles = MainApp.getPlayer().getInventory().getCollectibles();
        ArrayList<String> collectibleNames = new ArrayList<>();
        for (Collectible collectible : availableCollectibles) {
            collectibleNames.add(collectible.getName());
            ((Interactible) GUIManager.getPanel("prefight")).addButtonListener(collectibleButtonListener, collectible.getName());
        }
        return collectibleNames;
    }

    public static ArrayList<String> getActiveWeapons() {
        ArrayList<Weapon> availableWeapons = MainApp.getPlayer().getInventory().getWeapons();
        ArrayList<String> weaponNames = new ArrayList<>();
        for (Weapon weapon : availableWeapons) {
            weaponNames.add(weapon.getName());
            ((Interactible) GUIManager.getPanel("prefight")).addButtonListener(weaponButtonListener, weapon.getName());
        }
        return weaponNames;
    }


    public static void init(){
        ((Interactible)GUIManager.getPanel("prefight")).addButtonListener(confirmButtonListener, "confirm");
        ((PrefightPanel) GUIManager.getPanel("prefight")).refreshButtons();
    }
}
