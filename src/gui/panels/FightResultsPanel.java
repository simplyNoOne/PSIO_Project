package gui.panels;

import interfaces.Interactible;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;

public class FightResultsPanel extends CustomPanel implements Interactible {

    class OkButton extends JButton {

        static final int BUTTON_WIDTH = 250;
        static final int BUTTON_HEIGHT = 50;

        OkButton() {
            super();
            this.setBorderPainted(false);
            this.setFocusPainted(false);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            this.setBackground(new Color(5, 5, 5));
            this.setForeground(new Color(250, 250, 250));
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
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

    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;

    private OkButton okButton;
    private ResultsMessage resultMessage;
    public FightResultsPanel(){

        super();

        resultMessage = new ResultsMessage();
        this.add(resultMessage);

        this.setBackground(new Color(0, 0, 0, 220));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);



        okButton = new OkButton();
        okButton.setText("Proceed");
        int okButtonX = (PANEL_WIDTH - OkButton.BUTTON_WIDTH)/2;
        int okButtonY = 220;
        okButton.setBounds(okButtonX, okButtonY, OkButton.BUTTON_WIDTH, OkButton.BUTTON_HEIGHT);
        this.add(okButton);


    }

    public void updateMessage(){
        if(ManagerHandler.getFightManager().getPrevFightWon())
            resultMessage.setText("Congrats, you defeated the opponent!");
        else
            resultMessage.setText("Sorry, seems you've lost this fight.");

    }


    public void addButtonListener(ActionListener listener, String buttonId){
        okButton.addActionListener(listener);
    }

}