package managers;

import data.Quiz;
import gui.panels.ColorGamePanel;
import gui.panels.QuizPanel;
import interfaces.Interactible;
import main.StateMachine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PuzzleManager {

    public static class QuizAnswerButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((QuizPanel.QuizButton) e.getSource()).isEnabled()) {
                String answer = ((JButton) e.getSource()).getText();
                if (answer.equals(actualQuiz.getCorrect_answer()))
                    StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
                else
                    StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);

                StateMachine.nextState();
            }
        }
    }

    public static class SliderChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            ((ColorGamePanel)GUIManager.getPanel("colorgame")).setPreviewColor(((JSlider) e.getSource()).getName(), ((JSlider) e.getSource()).getValue());
            switch (((JSlider) e.getSource()).getName()){
                case "red" -> currentColor = new Color(((JSlider) e.getSource()).getValue(), currentColor.getGreen(), currentColor.getBlue());
                case "green" -> currentColor = new Color(currentColor.getRed(), ((JSlider) e.getSource()).getValue(), currentColor.getBlue());
                case "blue" -> currentColor = new Color(currentColor.getRed(), currentColor.getGreen(), ((JSlider) e.getSource()).getValue());
            }
            ((ColorGamePanel)GUIManager.getPanel("colorgame")).specialRepaint();
        }
    }

    public static class ColorGameConfirmButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ((ColorGamePanel)GUIManager.getPanel("colorgame")).setCurrentColorColor(currentColor);
            checkIfColorIsCorrect();
        }
    }

    private static final QuizAnswerButtonListener quizAnswerButtonListener = new QuizAnswerButtonListener();
    private static SliderChangeListener sliderChangeListener = new SliderChangeListener();
    private static ColorGameConfirmButtonListener colorGameConfirmButtonListener = new ColorGameConfirmButtonListener();
    private static ArrayList<Quiz> allQuestions = loadAllQuestions();
    private static Quiz actualQuiz;
    private static String puzzleType;
    private static Color expectedColor;
    private static Color currentColor = new Color(255, 255, 255);
    private static int tolerance = 20;
    private static int leftChanceNumber;




    public static void init() {
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "0");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "1");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "2");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "3");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "red");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "green");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addSliderChangeListener(sliderChangeListener, "blue");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addButtonListener(colorGameConfirmButtonListener, "empty");
    }

    public static void newPuzzle(){

        if(new Random().nextInt(0, 2) == 0)
             {
            GUIManager.addPanel("quiz", "game");
            refreshQuiz();
            GUIManager.getPane("game").repaint();
            puzzleType = "quiz";
        }
        else {
            GUIManager.addPanel("colorgame", "game");
            refreshColorGame();
            GUIManager.getPane("game").repaint();
            puzzleType = "colorgame";
        }
    }

    public static void refreshColorGame(){
        leftChanceNumber = 3;
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).resetPanel();
        int red = new Random().nextInt(0, 256);
        int green = new Random().nextInt(0, 256);
        int blue = new Random().nextInt(0, 256);
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).setExpectedColor(new Color(red, green, blue));
        expectedColor = new Color(red, green, blue);
        System.out.println(expectedColor);
    }

    public static void refreshQuiz(){
        ((QuizPanel)GUIManager.getPanel("quiz")).setAllButtonsAsInactive();
        generateRandomQuiz();
        ArrayList<String> answers = actualQuiz.getAnswers();
        for(int answerId = 0; answerId < answers.size(); answerId++)
        {
            ((QuizPanel)GUIManager.getPanel("quiz")).setAnswerButtonText(Integer.toString(answerId), answers.get(answerId));
        }
        ((QuizPanel)GUIManager.getPanel("quiz")).setQuestionContent(actualQuiz.getQuestion());
    }



    public static void generateRandomQuiz()
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


    private static ArrayList<Quiz> loadAllQuestions() {

        ArrayList<Quiz> quizList = new ArrayList<>();
        try {
            File questionsFile = new File("resources\\questions.txt");
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

    public static String getPuzzleType() {
        return puzzleType;
    }

    private static void checkIfColorIsCorrect() {
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

        if(redCorrect && greenCorrect && blueCorrect){
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            StateMachine.nextState();
        }
        else {
            if (leftChanceNumber > 0) {
                leftChanceNumber--;
                ((ColorGamePanel) GUIManager.getPanel("colorgame")).setLeftChancesLabel(leftChanceNumber);
                if (!redCorrect)
                    ((ColorGamePanel) GUIManager.getPanel("colorgame")).setSliderIsEnabled("red", true);
                if (!greenCorrect)
                    ((ColorGamePanel) GUIManager.getPanel("colorgame")).setSliderIsEnabled("green", true);
                if (!blueCorrect)
                    ((ColorGamePanel) GUIManager.getPanel("colorgame")).setSliderIsEnabled("blue", true);
            } else {
                StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
                StateMachine.nextState();
            }
        }
    }
}
