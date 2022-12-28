package gui.panels;

import javax.swing.*;
import java.awt.*;

abstract public class StatsPanel extends CustomPanel{

    protected static int PANEL_WIDTH = 200;
    protected static int PANEL_HEIGHT = 250;

    public class ValueLabel extends JLabel {
        ValueLabel(){
            this.setForeground(Color.white);

            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        }
    }

    public class TitleLabel extends JLabel {
        TitleLabel(String text){
            this.setForeground(Color.white);
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
            this.setText(text);
        }
    }

    StatsPanel(){

        super();

        this.setBackground(new Color(0, 0, 0, 150));

    }

    public abstract void updateStats();

}
