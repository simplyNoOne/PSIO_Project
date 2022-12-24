package managers;

import data.Texture;
import gui.panels.Fight.buttons.CarryOutTheFightButton;
import gui.panels.FightResults.FightResultsPanel;
import gui.panels.FightResults.buttons.OkButton;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightResultsManager {

    private static final int TITLE_BAR_HEIGHT = 31;
    private static final OkButtonListener okButtonListener = new OkButtonListener();

    public static class OkButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button has been clicked!");
            StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);
            StateMachine.nextState();
        }
    }


    public static OkButton getOkButton()
    {
        Texture okButtonTexture = new Texture(ResourceManager.getTexture("ok").getTexturePath());
        int x0 = (GUIManager.getWidth() - okButtonTexture.getIconWidth())/2;
        int y0 = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - okButtonTexture.getIconHeight())/2;
        OkButton okButton = new OkButton(okButtonTexture);
        okButton.setBounds(x0, y0, okButtonTexture.getIconWidth(), okButtonTexture.getIconHeight());
        okButton.addActionListener(okButtonListener);

        return okButton;
    }

}
