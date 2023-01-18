package gui.panels;

import generators.LootGenerator;
import gui.CustomButton;
import interfaces.Interactible;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;

public class FightResultsPanel extends CustomPanel implements Interactible {

    class OkButton extends CustomButton {

        static final int BUTTON_WIDTH = 250;
        static final int BUTTON_HEIGHT = 50;

        OkButton() {
            super();
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
        }
    }

    class ResultsMessage extends JLabel{
        ResultsMessage(){
            this.setHorizontalAlignment(CENTER);
            this.setBounds((PANEL_WIDTH - 400)/2, 50, 400, 50);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setForeground(Color.white);
            this.setBackground(new Color(0,0,0,0));
        }
    }

    class BonusMessage extends JLabel{
        BonusMessage(){
            this.setHorizontalAlignment(CENTER);
            this.setBounds((PANEL_WIDTH - 400)/2 + 50, 115, 400, 50);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setForeground(Color.white);
            this.setBackground(new Color(0,0,0,0));
        }
    }

    class BonusIcon extends JLabel{
        BonusIcon(){
            super();
            this.setBounds((PANEL_WIDTH - 400)/2-50, 110, 64, 64);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setForeground(Color.white);

        }

        void addTexture(String texture){this.setIcon(ManagerHandler.getResourceManager().getTexture(texture));}
        void removeTexture(){
            this.setIcon(null);
            bonusIcon.setText("");
        }
    }

    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;

    private OkButton okButton;
    private ResultsMessage resultMessage;
    private BonusMessage bonusMessage;
    private BonusIcon bonusIcon;
    public FightResultsPanel(){

        super();

        resultMessage = new ResultsMessage();
        bonusMessage = new BonusMessage();
        this.add(resultMessage);
        this.add(bonusMessage);

        bonusIcon = new BonusIcon();
        this.add(bonusIcon);

        this.setBackground(new Color(0, 0, 0, 220));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);



        okButton = new OkButton();
        okButton.setText("proceed");
        int okButtonX = (PANEL_WIDTH - OkButton.BUTTON_WIDTH)/2;
        int okButtonY = 210;
        okButton.setBounds(okButtonX, okButtonY, OkButton.BUTTON_WIDTH, OkButton.BUTTON_HEIGHT);
        this.add(okButton);


    }

    public void updateMessage(){
        if(ManagerHandler.getFightManager().getPrevFightWon()) {
            resultMessage.setText("Congrats, you defeated the opponent!");
            prepBonus();
        }
        else {
            resultMessage.setText("Sorry, seems you've lost this fight.");
            bonusMessage.setText("");
            bonusIcon.removeTexture();
        }

    }

    private void prepBonus(){
        String bonus = LootGenerator.getBonus();
        if (bonus.equals("armor")){
            bonusIcon.removeTexture();
            bonusIcon.setText("  +" +LootGenerator.getArmorGranted());
            bonusMessage.setText("Great news. Your armor was improved!");
        } else if (bonus.contains("collectible")) {
            String num = bonus.substring(11);
            System.out.println(num);
            bonusIcon.addTexture("col" + num);
            bonusIcon.setText("");
            bonusMessage.setText("For your efforts you receive a collectible");
        }else{
            bonusIcon.addTexture(bonus);
            bonusMessage.setText("New weapon in the inventory: "+ bonus);
        }
    }


    public void addButtonListener(ActionListener listener, String buttonId){
        okButton.addActionListener(listener);
    }

}