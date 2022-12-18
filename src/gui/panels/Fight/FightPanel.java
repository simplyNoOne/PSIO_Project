package gui.panels.Fight;

import gui.panels.CustomPanel;
import managers.FightManager;

import java.awt.event.ActionListener;

public class FightPanel extends CustomPanel {

    public FightPanel(){
        super();
        this.add(FightManager.getConfirmButton());

    }

}
