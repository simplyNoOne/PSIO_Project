package gui.panels;

import gui.CustomButton;
import interfaces.Interactible;
import main.ManagerHandler;
import managers.GUIManager;
import managers.ScoreManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoresPanel extends CustomPanel implements Interactible {

    static class ScoresButton extends CustomButton {
        static final int BUTTON_WIDTH = 170;
        static final int BUTTON_HEIGHT = 45;
        static final int POS_X = (PANEL_WIDTH - ScoresButton.BUTTON_WIDTH) / 2;
        static final int POS_Y = 410;

        ScoresButton() {
            super();
        }

        ScoresButton(String text) {
            this();
            this.setText(text);
        }
    }

    public class ScoreLabel extends JLabel {
        ScoreLabel(int score){
            this.setForeground(Color.white);
            this.setSize(200, 50);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
            this.setText(Integer.toString(score));
            ScoresPanel.this.add(this);
        }
    }

    public class PlaceLabel extends JLabel {
        PlaceLabel(int place){
            this.setForeground(Color.white);
            this.setSize(100, 50);
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            this.setText(place+".");
            ScoresPanel.this.add(this);
        }
    }

    public class NicknameLabel extends JLabel {
        NicknameLabel(String text){
            this.setForeground(Color.white);
            this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            this.setText(text);
            this.setSize(200, 50);
            ScoresPanel.this.add(this);
        }
    }

    static int PANEL_WIDTH = 450;
    private static int PANEL_HEIGHT = 500;
    private static final int MAX_SCORES_ON_PANEL = 7;

    ScoresButton backButton = new ScoresButton("back");

    public ScoresPanel(){
        super();
        this.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);
        //this.setPreferredSize(new Dimension(600, 600));

        backButton.setBounds(ScoresButton.POS_X, ScoresButton.POS_Y, ScoresButton.BUTTON_WIDTH, ScoresButton.BUTTON_HEIGHT);

        this.add(backButton);
        this.setBackground(new Color(0, 0, 0, 225));
        this.setVisible(true);
    }

    public void setScoresContent(SortedSet<Map.Entry<String, Integer>> sortedScores) {

        AtomicInteger i = new AtomicInteger();
        sortedScores.forEach((entry)->
        {
            if (i.get() < MAX_SCORES_ON_PANEL)
            {
                int space = 50+50* i.get();

                JLabel label1 = new PlaceLabel(i.get() +1);
                JLabel label2 = new NicknameLabel(entry.getKey());
                JLabel label3 = new ScoreLabel(entry.getValue());
                label1.setLocation(83,space);
                label2.setLocation(113, space);
                label3.setLocation(280,space);
                if (i.get() == 0)
                {
                    label1.setForeground(new Color(255,215,0));
                    label2.setForeground(new Color(255,215,0));
                    label3.setForeground(new Color(255,215,0));

                    label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
                    label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
                    label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));

                    label1.setLocation(63,space);
                    label2.setLocation(113, space);
                    label3.setLocation(286,space);

                }
                else if (i.get() ==1)
                {
                    label1.setForeground(new Color(196,202,206));
                    label2.setForeground(new Color(196,202,206));
                    label3.setForeground(new Color(196,202,206));

                    label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 31));
                    label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 31));
                    label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 31));

                } else if (i.get() ==2)
                {
                    label1.setForeground(new Color(176,141,87));
                    label2.setForeground(new Color(176,141,87));
                    label3.setForeground(new Color(176,141,87));

                    label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
                    label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
                    label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));

                }
                i.getAndIncrement();
            }


        });
    }

    public void addButtonListener(ActionListener listener, String buttonId){
        backButton.addActionListener(listener);
    }
}
