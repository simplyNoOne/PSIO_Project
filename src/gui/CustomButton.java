package gui;

import controller.Managers.Texture;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

abstract public class CustomButton extends JButton {

    protected CustomButton(){
        super();
        this.setFocusPainted(false);
    }

    protected CustomButton(Texture texture){
        this.setIcon(texture);
        this.setIcon(texture);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setFocusable(false);
        this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
        this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        this.setFocusPainted(false);
    }
}
