
package gui.panels;

import gui.panels.CustomPanel;
import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FightPanel extends CustomPanel implements Interactible {


    private final static int PANEL_WIDTH = 600;
    private final static int PANEL_HEIGHT = 300;

    private CustomMessage message;

    private class CustomMessage extends JTextPane{
        CustomMessage(){
            super();


            StyledDocument doc = this.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            this.setFocusable(false);
            this.setAlignmentX(0.5f);
            this.setEditable(false);
            this.getCaret().deinstall( this );        //this line is SACRED, it makes the text area not selectable, non-editable, totally unresponsive
            this.setBounds((PANEL_WIDTH - 500)/2, 100, 500, 80);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            this.setForeground(Color.white);
            this.setBackground(new Color(0,0,0,0));

        }
    }

    public FightPanel(){

        super();

        message = new CustomMessage();

        this.add(message);

        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);



    }

    public void addButtonListener(ActionListener listener, String buttonId){

    }

    public void setMessage(String message){this.message.setText(message);}

}

