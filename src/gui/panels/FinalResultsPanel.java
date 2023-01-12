package gui.panels;

import com.sun.tools.javac.Main;
import gui.CustomButton;
import gui.panels.CustomPanel;
import interfaces.Interactible;
import main.MainApp;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;


public class FinalResultsPanel extends CustomPanel implements Interactible {


    class OkButton extends CustomButton {

        static final int BUTTON_WIDTH = 260;
        static final int BUTTON_HEIGHT = 50;

        OkButton() {
            super();
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
        }
    }

    class ResultsMessage extends JLabel{
        ResultsMessage(){
            this.setHorizontalAlignment(CENTER);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setForeground(Color.white);
            this.setBackground(new Color(0,0,0,0));
        }
    }

    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;

    private final OkButton okButton;
    private ResultsMessage headerMessage;

    private ResultsMessage scoreMessage;
    public FinalResultsPanel(){

        super();

        headerMessage = new ResultsMessage();
        headerMessage.setBounds((PANEL_WIDTH - 400)/2, 50, 400, 50);
        this.add(headerMessage);

        scoreMessage = new ResultsMessage();
        scoreMessage.setBounds((PANEL_WIDTH - 500)/2, 110, 500, 50);
        this.add(scoreMessage);

        this.setBackground(new Color(0, 0, 0, 220));
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);



        okButton = new OkButton();
        okButton.setText("back to menu");
        int okButtonX = (PANEL_WIDTH - OkButton.BUTTON_WIDTH)/2;
        int okButtonY = 210;
        okButton.setBounds(okButtonX, okButtonY, OkButton.BUTTON_WIDTH, OkButton.BUTTON_HEIGHT);
        this.add(okButton);


    }

    public void updateMessage(){
            headerMessage.setText("Bad news, " + MainApp.getPlayer().getName() + ", you've died... ");
            scoreMessage.setText("Great job though, you've scored " + MainApp.getPlayer().getScore() + " points!");

    }
    public void addButtonListener(ActionListener listener, String buttonId){
        okButton.addActionListener(listener);
    }

}