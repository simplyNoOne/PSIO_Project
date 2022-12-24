package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

abstract public class CustomPanel extends JPanel {

    public CustomPanel(){
        this.setLayout(null);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
