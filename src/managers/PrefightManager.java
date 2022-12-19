package managers;

import com.sun.tools.javac.Main;
import data.Collectible;
import data.Texture;
import data.Weapon;
import gui.panels.Prefight.PrefightPanel;
import gui.panels.Prefight.buttons.CollectibleButton;
import gui.panels.Prefight.buttons.ConfirmButton;
import gui.panels.Prefight.buttons.WeaponButton;
import interfaces.Interactible;
import main.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrefightManager {

    private static final WeaponButtonListener weaponButtonListener = new WeaponButtonListener();
    private static final CollectibleButtonListener collectibleButtonListener = new CollectibleButtonListener();
    private static final ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();


    static PrefightPanel panel;
    public static class WeaponButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String weaponName = ((PrefightPanel.WPFButton) e.getSource()).name;
            System.out.println(weaponName + " has been clicked!");
//            MainApp.getPlayer().getInventory().setActiveWeapon(tag);
        }
    }

    public static class CollectibleButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String collectibleName = ((PrefightPanel.CPFButton) e.getSource()).name;
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



    public static void initWeaponButtons() {
        ArrayList<Weapon> availableWeapons = MainApp.getPlayer().getInventory().getWeapons();

        for (int id = 0; id < availableWeapons.size(); id++) {
            Weapon weapon = availableWeapons.get(id);
            Texture weaponTexture = ResourceManager.getTexture(weapon.getName());
            panel.addWeaponButton(weaponTexture, "weapon" + Integer.toString( id + 1), weapon.getName());
            ((Interactible)panel).addButtonListener(weaponButtonListener, "weapon" + ( id + 1));
        }
    }

    public static void initCollectibleButtons() {
        ArrayList<Collectible> availableCollectibles = MainApp.getPlayer().getInventory().getCollectibles();

        for (int id = 0; id < availableCollectibles.size(); id++) {
            Collectible collectible = availableCollectibles.get(id);
            Texture collectibleTexture = ResourceManager.getTexture(collectible.getName());
            panel.addCollectibleButton(collectibleTexture, "collectible" + ( id + 1), collectible.getName());
            ((Interactible)panel).addButtonListener(collectibleButtonListener, "collectible" + ( id + 1));
        }
    }


    public static void init(){
        panel =  ((PrefightPanel)GUIManager.getPanel("prefight"));
        initCollectibleButtons();
        initWeaponButtons();
        ((Interactible)panel).addButtonListener(confirmButtonListener, "confirm");
        panel.updatePanel();
    }
}
