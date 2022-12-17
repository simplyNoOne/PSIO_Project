package gui.panels.Fight;

import gui.panels.CustomPanel;

public class FightPanel extends CustomPanel {

    public FightPanel(){
        super();
        this.add(FightManager.getConfirmButton());

    }
}
