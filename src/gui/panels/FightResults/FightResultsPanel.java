//package gui.panels;
//
//import controller.Managers.PreFightManager;
//import controller.Managers.ResourceManager;
//import data.Weapon;
//import gui.GUIManager;
//import gui.buttons.CollectibleButton;
//import gui.buttons.ConfirmButton;
//import gui.buttons.WeaponButton;
//import main.Main;
//
//import java.util.ArrayList;
//
//public class FightResultsPanel extends CustomPanel{
//
//    static final int HORIZONTAL_BUTTON_SPACE = 30;
//    static final int VERTICAL_BUTTON_SPACE = 50;
//    static final int WPN_BUTTON_WIDTH = 150;
//    static final int WPN_BUTTON_HEIGHT = 150;
//
//    static final int CLC_BUTTON_WIDTH = 150;
//    static final int CLC_BUTTON_HEIGHT = 150;
//
//    static final int CON_BUTTON_WIDTH = 500;
//    static final int CON_BUTTON_HEIGHT = 50;
//
//    ArrayList<WeaponButton> weaponButtons;
//    ArrayList<CollectibleButton> nonWeaponItemButtons;
//    ConfirmButton confirmButton;
//
//
//    public FightResultsPanel(){
//        super();
//
////      ArrayList<WeaponButton> weaponButtons = new ArrayList<WeaponButton>(); //Probably unnecessary
//        ArrayList<Weapon> availableWeapons = Main.getPlayer().getInventory().getWeapons();
//
//        int weaponBtnX0 = (GUIManager.getWidth() - availableWeapons.size() * WPN_BUTTON_WIDTH - (availableWeapons.size()-1)*HORIZONTAL_BUTTON_SPACE)/2;
//        int weaponBtnY0 = (GUIManager.getHeight() - WPN_BUTTON_HEIGHT - CollectibleButton.HEIGHT - VERTICAL_BUTTON_SPACE)/2;
//
//        for (int weaponId = 0; weaponId < availableWeapons.size(); weaponId++) {
//            WeaponButton weaponButton = new WeaponButton(availableWeapons.get(weaponId).getTag(), ResourceManager.getTexture(availableWeapons.get(weaponId).getName()));
//            weaponButton.setBounds(weaponBtnX0 + weaponId*(WPN_BUTTON_WIDTH + HORIZONTAL_BUTTON_SPACE), weaponBtnY0, WPN_BUTTON_WIDTH, 150);
//            weaponButton.addActionListener(PreFightManager.getWeaponButtonListener());
//            this.add(weaponButton);
//        }
//    }
//}
