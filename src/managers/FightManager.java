package managers;

import data.Texture;
import gui.panels.Fight.buttons.CarryOutTheFightButton;
import main.StateMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FightManager {

    private static final int TITLE_BAR_HEIGHT = 31;
    private static final CarryOutTheFightButtonListener carryOutTheFightButtonListener = new CarryOutTheFightButtonListener();

    public static class CarryOutTheFightButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Carry out the fight button has been clicked!");
            int nextState = (new Random()).nextInt(0, 3);
            if(nextState==0) StateMachine.setNextStateVar(StateMachine.State.PUZZLE_OR_FIGHT);
            if(nextState==1) StateMachine.setNextStateVar(StateMachine.State.FINAL_RESULTS);
            else StateMachine.setNextStateVar(StateMachine.State.LEVELUP);
            StateMachine.nextState();
        }
    }


    public static CarryOutTheFightButton getConfirmButton()
    {
        Texture carryOutTheFightTexture = new Texture(ResourceManager.getTexture("carry_out_the_battle").getTexturePath());
        int x0 = (GUIManager.getWidth() - carryOutTheFightTexture.getIconWidth())/2;
        int y0 = (GUIManager.getHeight() - TITLE_BAR_HEIGHT - carryOutTheFightTexture.getIconHeight())/2;
        CarryOutTheFightButton carryOutTheFightButton = new CarryOutTheFightButton(carryOutTheFightTexture);
        carryOutTheFightButton.setBounds(x0, y0, carryOutTheFightTexture.getIconWidth(), carryOutTheFightTexture.getIconHeight());
        carryOutTheFightButton.addActionListener(carryOutTheFightButtonListener);

        return carryOutTheFightButton;
    }

}
