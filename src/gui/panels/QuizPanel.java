package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class QuizPanel extends CustomPanel implements Interactible {
    public class QuizButton extends JButton {

        QuizButton(){
            super();
            this.setFocusPainted(false);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);

            this.setBorder(new BasicBorders.ButtonBorder(Color.black, Color.black, Color.black, Color.black));
        }
        QuizButton(String text){
            this();
            this.setText(text);
            this.setFont(new Font("SansSerif", Font.BOLD, 30 ));
        }

    }

    private final static int PANEL_WIDTH = 700;
    private final static int PANEL_HEIGHT = 500;
    private final static int BUTTON_SPACE = 30;
    private JTextPane question;
    private Map<String, QuizButton> answerButtons = new HashMap<>();
    public QuizPanel(){

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
        answerButtons.get(buttonId).setEnabled(true);

    }

    public void setQuestionContent(String text){
        question.setText(text);
    }

    private void addAnswerButton(String buttonId){
        QuizButton answerButton = new QuizButton();
        answerButton.setName(buttonId);
        int x0 = 50;
        int y0 = 210;
        int width = 290;
        int height = 90;

        switch (answerButtons.entrySet().size()){
            case 1 -> x0 = 360;
            case 2 -> y0 = 345;
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
        question = new JTextPane();
        question.setAlignmentX(1);
        question.setAlignmentY(1);
        question.setBackground(Color.WHITE);
        question.setBounds(50, 10, 600, 150);
        question.setEditable(false);
    }

    public void addButtonListener(ActionListener listener, String buttonId){
        answerButtons.get(buttonId).addActionListener(listener);
    }

    public void setAllButtonsAsInactive()
    {
        for(Map.Entry set: answerButtons.entrySet())
        {
            ((QuizButton) set.getValue()).setEnabled(false);
            ((QuizButton) set.getValue()).setText(null);
        }
    }


}