package gui.panels.Prefight;

import gui.panels.CustomPanel;
import gui.panels.Prefight.buttons.CollectibleButton;
import gui.panels.Prefight.buttons.WeaponButton;
import managers.PrefightManager;

import java.awt.event.ActionListener;
import java.util.ArrayList;



public class PrefightPanel extends CustomPanel {
    public PrefightPanel(){
        super();

        ArrayList<WeaponButton> weaponButtons = PrefightManager.getAllWeaponButtons();
        for(WeaponButton weaponButton : weaponButtons)
            this.add(weaponButton);

        ArrayList<CollectibleButton> collectibleButtons = PrefightManager.getAllCollectibleButtons();
        for(CollectibleButton collectibleButton : collectibleButtons)
            this.add(collectibleButton);

        this.add(PrefightManager.getConfirmButton());

    }


}
