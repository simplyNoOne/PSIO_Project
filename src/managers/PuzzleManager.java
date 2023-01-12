package managers;

import data.Quiz;
import gui.panels.ColorGamePanel;
import gui.panels.QuizPanel;
import interfaces.Interactible;
import interfaces.ScoreModifier;
import main.ManagerHandler;
import main.StateMachine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PuzzleManager implements ScoreModifier {

    public static class QuizAnswerButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((QuizPanel.QuizButton) e.getSource()).isEnabled()) {
                String answer = ((JButton) e.getSource()).getText();
                answer = answer.replace("<html><center>", "").replace("</center></html>", "");
                if(answer.equals(actualQuiz.getCorrect_answer())){
                    StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
                    puzzleAnsweredRight = true;
                }
                else{
                    StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
                    puzzleAnsweredRight = false;
                }

                StateMachine.nextState();
            }
        }
    }

    public static class SliderChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setPreviewColor(((JSlider) e.getSource()).getName(), ((JSlider) e.getSource()).getValue());
            switch (((JSlider) e.getSource()).getName()){
                case "red" -> currentColor = new Color(((JSlider) e.getSource()).getValue(), currentColor.getGreen(), currentColor.getBlue());
                case "green" -> currentColor = new Color(currentColor.getRed(), ((JSlider) e.getSource()).getValue(), currentColor.getBlue());
                case "blue" -> currentColor = new Color(currentColor.getRed(), currentColor.getGreen(), ((JSlider) e.getSource()).getValue());
            }
            ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).revalidate();
        }
    }

    public static class ColorGameConfirmButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setCurrentColorColor(currentColor);
            checkIfColorIsCorrect();
        }
    }

    private static final QuizAnswerButtonListener quizAnswerButtonListener = new QuizAnswerButtonListener();
    private static SliderChangeListener sliderChangeListener = new SliderChangeListener();
    private static ColorGameConfirmButtonListener colorGameConfirmButtonListener = new ColorGameConfirmButtonListener();
    private ArrayList<Quiz> allQuestions = loadAllQuestions();
    private static Quiz actualQuiz;
    private static String puzzleType;
    private static Color expectedColor;
    private static Color currentColor = new Color(255, 255, 255);
    private static final int tolerance = 20;
    private static int leftChanceNumber;
    private static boolean colorGameFinished = false;
    private static boolean puzzleAnsweredRight = false;




    public void init() {
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "0");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "1");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "2");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "3");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "red");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "green");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "blue");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addButtonListener(colorGameConfirmButtonListener, "empty");
    }

    public void newPuzzle(){

        if(!(new Random().nextInt(0, 2) == 0))
        {
            puzzleType = "quiz";
            ManagerHandler.getGUIManager().addPanel("quiz", "game");
            refreshQuiz();
            ManagerHandler.getGUIManager().getPane("game").repaint();
        }
        else {
            puzzleType = "colorgame";
            ManagerHandler.getGUIManager().addPanel("colorgame", "game");
            refreshColorGame();
            ManagerHandler.getGUIManager().getPane("game").repaint();
        }
    }

    public void refreshColorGame(){
        leftChanceNumber = 5;
        colorGameFinished = false;
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).resetPanel();
        int red = new Random().nextInt(0, 256);
        int green = new Random().nextInt(0, 256);
        int blue = new Random().nextInt(0, 256);
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setExpectedColor(new Color(red, green, blue));
        expectedColor = new Color(red, green, blue);
        currentColor = new Color(127, 127, 127);
        System.out.println(expectedColor);
    }

    public void refreshQuiz(){
        ((QuizPanel)ManagerHandler.getGUIManager().getPanel("quiz")).setAllButtonsAsInactive();
        generateRandomQuiz();
        ArrayList<String> answers = actualQuiz.getAnswers();
        for(int answerId = 0; answerId < answers.size(); answerId++)
        {
            ((QuizPanel)ManagerHandler.getGUIManager().getPanel("quiz")).setAnswerButtonText(Integer.toString(answerId), answers.get(answerId));
        }
        ((QuizPanel)ManagerHandler.getGUIManager().getPanel("quiz")).setQuestionContent(actualQuiz.getQuestion());
    }

    public void generateRandomQuiz()
    {
        if(allQuestions.size() == 1)
            actualQuiz = allQuestions.get(0);
        else {
            int puzzleId = new Random().nextInt(0, allQuestions.size());
            actualQuiz = allQuestions.get(puzzleId);
        }

        Collections.shuffle(actualQuiz.getAnswers());

        allQuestions.remove(actualQuiz);
        if(allQuestions.size() == 0)
            allQuestions = loadAllQuestions();
    }


    private ArrayList<Quiz> loadAllQuestions() {

        ArrayList<Quiz> quizList = new ArrayList<>();
        try {
            File questionsFile = new File("resources" + File.separator + "questions.txt");
            Scanner myReader = new Scanner(questionsFile);
            while (myReader.hasNextLine()) {
                String row = myReader.nextLine();
                String[] rowTable = row.split("\t");
                String question = rowTable[0];
                String[] answers = rowTable[1].replace("<", "").replace(">", "").split(";");
                int correctAnswerId = Integer.parseInt(rowTable[2]);
                Quiz quiz = new Quiz();
                quiz.setQuestion(question);
                for (String answer : answers) quiz.addAnswer(answer);
                quiz.setCorrect_answer(answers[correctAnswerId]);
                quizList.add(quiz);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return quizList;
    }

    public String getPuzzleType() {
        return puzzleType;
    }
    @Override
    public int getScoreModifier() {
        if (puzzleAnsweredRight)
            if(getPuzzleType().equals("quiz"))
                return actualQuiz.getScoreModifier();
            else
                return 100 + leftChanceNumber*10;
        else
            return -100;
    }



    private static void checkIfColorIsCorrect() {
        System.out.println(currentColor);
        boolean redCorrect = false;
        boolean greenCorrect = false;
        boolean blueCorrect = false;

        System.out.println(leftChanceNumber);
        if(Math.abs(expectedColor.getRed() - currentColor.getRed()) <= tolerance)
            redCorrect = true;

        if(Math.abs(expectedColor.getGreen() - currentColor.getGreen()) <= tolerance)
            greenCorrect = true;

        if(Math.abs(expectedColor.getBlue() - currentColor.getBlue()) <= tolerance)
            blueCorrect = true;

        if(!redCorrect || !greenCorrect || !blueCorrect){
            if (leftChanceNumber > 0) {
                leftChanceNumber--;
                ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setLeftChancesLabel(leftChanceNumber);
                if (redCorrect)
                    ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("red", false, true);
                if (greenCorrect)
                    ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("green", false, true);
                if (blueCorrect)
                    ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("blue", false, true);
            }
            else {
                if(!redCorrect) ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("red", false, false);
                if(!greenCorrect) ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("green", false, false);
                if(!blueCorrect) ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("blue", false, false);
                colorGameFinished = true;
                puzzleAnsweredRight = false;
                StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
            }
        }
        else {
            ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("red", false, true);
            ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("green", false, true);
            ((ColorGamePanel) ManagerHandler.getGUIManager().getPanel("colorgame")).setSliderAsFinal("blue", false, true);
            colorGameFinished = true;
            puzzleAnsweredRight = true;
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
        }
    }

    public boolean isColorGameFinished() {
        return colorGameFinished;
    }

    public void setColorGameFinished(boolean colorGameFinished) {
        PuzzleManager.colorGameFinished = colorGameFinished;
    }

    public boolean isPuzzleAnsweredRight() {
        return puzzleAnsweredRight;
    }
}

