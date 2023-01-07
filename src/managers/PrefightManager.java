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

    private final WeaponButtonListener weaponButtonListener = new WeaponButtonListener();
    private final CollectibleButtonListener collectibleButtonListener = new CollectibleButtonListener();
    private final ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();


    public ArrayList<String> getActiveCollectibles() {
        ArrayList<Collectible> availableCollectibles = MainApp.getPlayer().getInventory().getCollectibles();
        ArrayList<String> collectibleNames = new ArrayList<>();
        for (Collectible collectible : availableCollectibles) {
            collectibleNames.add(collectible.getName());
            ((Interactible) ManagerHandler.getGUIManager().getPanel("prefight")).addButtonListener(collectibleButtonListener, collectible.getName());
        }
        return collectibleNames;
    }

    public ArrayList<String> getActiveWeapons() {
        ArrayList<Weapon> availableWeapons = MainApp.getPlayer().getInventory().getWeapons();
        ArrayList<String> weaponNames = new ArrayList<>();
        for (Weapon weapon : availableWeapons) {
            weaponNames.add(weapon.getName());
            ((Interactible) ManagerHandler.getGUIManager().getPanel("prefight")).addButtonListener(weaponButtonListener, weapon.getName());
        }
        return weaponNames;
    }


    public void init(){
        ((Interactible) ManagerHandler.getGUIManager().getPanel("prefight")).addButtonListener(confirmButtonListener, "confirm");
        ((PrefightPanel) ManagerHandler.getGUIManager().getPanel("prefight")).refreshButtons();
    }
}
