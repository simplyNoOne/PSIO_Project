package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;
import managers.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PuzzlePanel extends CustomPanel implements Interactible {
    class PuzzleButton extends JButton {

        static final int BUTTON_WIDTH = 150;
        static final int BUTTON_HEIGHT = 150;
        PuzzleButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        PuzzleButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));

        }
    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;
    private JLabel question;
    private Map<String, PuzzleButton> answerButtons = new HashMap<>();
    public PuzzlePanel(){

        super();



        this.setBackground(new Color(0, 0, 0));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        for(int i = 0; i < 4; i++){
            addAnswerButton(Integer.toString(i));
            this.add(answerButtons.get(Integer.toString(i)));
        }
        addQuestion();
        this.add(question);

    }

    public void setAnswerButtonText(String buttonId,String text){
        answerButtons.get(buttonId).setText(text);
    }

    public void setQuestionContent(String text){
        question.setText(text);
    }

    private void addAnswerButton(String buttonId){
        PuzzleButton answerButton = new PuzzleButton();
        answerButton.setName(buttonId);
        int x0 = 50;
        int y0 = 210;
        int width = 290;
        int height = 90;

        switch (answerButtons.entrySet().size()){
            case 1 -> {
                x0 = 360;
                y0 = 210;
            }
            case 2 -> {
                x0 = 50;
                y0 = 345;
            }
            case 3 -> {
                x0 = 360;
                y0 = 345;
            }
        }
        answerButton.setBounds(x0, y0, width, height);
        answerButtons.put(buttonId, answerButton);
        answerButton.setBackground(Color.GRAY);
    }


    private void addQuestion(){
        question = new JLabel();
        question.setOpaque(true);
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setVerticalAlignment(SwingConstants.CENTER);
        question.setBackground(Color.WHITE);
        question.setBounds(50, 10, 600, 150);
    }

    public void addButtonListener(ActionListener listener, String buttonId){
        answerButtons.get(buttonId).addActionListener(listener);
    }


}
