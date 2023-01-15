package gui;

import data.Texture;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

abstract public class CustomButton extends JButton {


    protected CustomButton(){
        super();
        this.setFocusable(false);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFocusable(false);
        this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.setFocusPainted(false);
    }
}
