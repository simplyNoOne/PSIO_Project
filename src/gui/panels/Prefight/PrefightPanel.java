package gui.panels.Prefight;

import gui.panels.CustomPanel;
import gui.panels.Prefight.buttons.CollectibleButton;
import gui.panels.Prefight.buttons.WeaponButton;
import managers.GUIManager;
import managers.PrefightManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class PrefightPanel extends CustomPanel {

    private static int PANEL_WIDTH = 700;
    private static int PANEL_HEIGHT = 500;
    public PrefightPanel(){
        super();
        this.setBackground(Color.cyan);
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        ArrayList<WeaponButton> weaponButtons = PrefightManager.getAllWeaponButtons();
        for(WeaponButton weaponButton : weaponButtons)
            this.add(weaponButton);

        ArrayList<CollectibleButton> collectibleButtons = PrefightManager.getAllCollectibleButtons();
        for(CollectibleButton collectibleButton : collectibleButtons)
            this.add(collectibleButton);

        this.add(PrefightManager.getConfirmButton());

    }
}
