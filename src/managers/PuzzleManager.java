package managers;

import data.Texture;
import gui.panels.Puzzle.buttons.OkButton;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PuzzleManager {

    private static final int TITLE_BAR_HEIGHT = 31;
    private static final OkButtonListener okButtonListener = new OkButtonListener();

    public static class OkButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok button has been clicked!");
            int nextState = (new Random()).nextInt(0, 2);
            if(nextState==0) StateMachine.setNextStateVar(StateMachine.State.PUZZLE_OR_FIGHT);
            else StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
            StateMachine.nextState();
        }
    }


    public static OkButton getOkButton()
    {
//      TODO in here I'll specify which stats will be upgraded + Stats will be displayed as a layers on this panel
        Texture okTexture = new Texture(ResourceManager.getTexture("ok").getTexturePath());
        int x0 = (GUIManager.getWidth() - okTexture.getIconWidth()) / 2;
        int y0 = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - okTexture.getIconHeight()) / 2;
        OkButton okButton = new OkButton(okTexture);
        okButton.setBounds(x0, y0, okTexture.getIconWidth(), okTexture.getIconHeight());
        okButton.addActionListener(okButtonListener);

        return okButton;
    }

}
