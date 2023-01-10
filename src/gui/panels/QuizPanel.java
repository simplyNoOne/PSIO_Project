package gui.panels;

import interfaces.Interactible;
import managers.GUIManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        this.setBackground(new Color(0, 0, 0, 220));
        super.setBounds((GUIManager.getWidth() - PANEL_WIDTH)/2, (GUIManager.getHeight() - PANEL_HEIGHT)/2, PANEL_WIDTH, PANEL_HEIGHT);

        this.setLayout(null);
        for(int i = 0; i < 4; i++){
            addAnswerButton(Integer.toString(i));
            this.add(answerButtons.get(Integer.toString(i)));
        }
        addQuestion();
        this.add(question);
        revalidate();

    }

    public void setAnswerButtonText(String buttonId, String text){
        String textInHtml = "";
        int maxCharInRow = 40;
        int actualCharNumber = 0;
        for(String word: text.split(" ")) {
            if(actualCharNumber + word.length() > maxCharInRow)
            {
                textInHtml += "<br>" ;
                actualCharNumber = 0;
            }
            textInHtml += word + " ";
            actualCharNumber += word.length() + 1;
        }
        answerButtons.get(buttonId).setText("<html><center>" + text + "</center></html>");
        answerButtons.get(buttonId).setEnabled(true);

    }

    public void setQuestionContent(String text){
        question.setText(text);
    }

    private void addAnswerButton(String buttonId){
        QuizButton answerButton = new QuizButton();
        answerButton.setName(buttonId);
        int x0 = 50;
        int y0 = 250;
        int width = 275;
        int height = 75;

        switch (answerButtons.entrySet().size()){
            case 1 -> x0 = 375;
            case 2 -> y0 = 375;
            case 3 -> {
                x0 = 375;
                y0 = 375;
            }
        }
        answerButton.setBounds(x0, y0, width, height);
        answerButtons.put(buttonId, answerButton);
        answerButton.setBackground(new Color(8, 8, 201));
        answerButton.setForeground(new Color(196,172,11));
        answerButton.setBorder(new LineBorder(new Color(196, 172, 11)));
    }


    private void addQuestion(){
        question = new JTextPane();
        question.setAlignmentX(1);
        question.setAlignmentY(1);
        question.setBackground(new Color(8, 8, 201));
        question.setBounds(50, 50, 600, 150);
        question.setEditable(false);
        question.setForeground(new Color(224, 199, 29));
        question.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 19));
        question.setBorder(new LineBorder(new Color(196, 172, 11)));

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