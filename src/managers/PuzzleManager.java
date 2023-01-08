package managers;

import data.Quiz;
import gui.panels.ColorGamePanel;
import gui.panels.QuizPanel;
import interfaces.Interactible;
import main.StateMachine;

import javax.swing.*;
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

    public static class MouseAdapterForColorGame extends MouseAdapter {
        private boolean redSet = false;
        private boolean greenSet = false;
        private boolean blueSet = false;
        private int redValue = 0;
        private int greenValue = 0;
        private int blueValue = 0;


        public void mouseClicked(MouseEvent e) {
            if (((JButton) e.getSource()).isEnabled()){
                ((ColorGamePanel) GUIManager.getPanel("colorgame")).setValueOnButton(((JButton)e.getSource()).getName(), ""+(255-e.getY()));
                switch (((JButton)e.getSource()).getName()){
                    case "red" ->{
                        redSet = true;
                        redValue = (255-e.getY());
                    }
                    case "green" ->{
                        greenSet = true;
                        greenValue = (255-e.getY());
                    }
                    case "blue" -> {
                        blueSet = true;
                        blueValue = (255-e.getY());
                    }
                }
                if(redSet && greenSet && blueSet){
                    checkIfColorIsCorrect(new Color(redValue, greenValue, blueValue));
                }
            }
        }



        public void mouseEntered(MouseEvent e){
            if (((JButton) e.getSource()).isEnabled()){
                ((ColorGamePanel) GUIManager.getPanel("colorgame")).showValueOnButton(((JButton)e.getSource()).getName(), ""+(255-e.getY()));

            }
        }

        public void setRedSet(boolean redSet) {
            this.redSet = redSet;
        }

        public void setGreenSet(boolean greenSet) {
            this.greenSet = greenSet;
        }

        public void setBlueSet(boolean blueSet) {
            this.blueSet = blueSet;
        }
    }


    private static final QuizAnswerButtonListener quizAnswerButtonListener = new QuizAnswerButtonListener();
    private static final
    MouseAdapterForColorGame colorButtonAdapter = new MouseAdapterForColorGame();

    private static ArrayList<Quiz> allQuestions = loadAllQuestions();
    private static Quiz actualQuiz;
    private static String puzzleType;
    private static Color expectedColor;
    private static int tolerance = 20;
    private static int leftChanceNumber;


    public static void init() {
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "0");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "1");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "2");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "3");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addMouseListener(colorButtonAdapter, "red");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addMouseListener(colorButtonAdapter, "green");
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).addMouseListener(colorButtonAdapter, "blue");
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

    private static void checkIfColorIsCorrect(Color color) {
        boolean redCorrect = false;
        boolean greenCorrect = false;
        boolean blueCorrect = false;

        System.out.println(leftChanceNumber);
        if(Math.abs(expectedColor.getRed() - color.getRed()) <= tolerance)
            redCorrect = true;

        if(Math.abs(expectedColor.getGreen() - color.getGreen()) <= tolerance)
            greenCorrect = true;

        if(Math.abs(expectedColor.getBlue() - color.getBlue()) <= tolerance)
            blueCorrect = true;

        if(redCorrect && greenCorrect && blueCorrect){
            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            StateMachine.nextState();
        }
        else
            if(leftChanceNumber > 0){
                leftChanceNumber--;
                if(!redCorrect) {
                    ((ColorGamePanel)GUIManager.getPanel("colorgame")).setColorButtonIsEnabled("red", true);
                    colorButtonAdapter.setRedSet(false);
                }
                if(!greenCorrect) {
                    ((ColorGamePanel)GUIManager.getPanel("colorgame")).setColorButtonIsEnabled("green", true);
                    colorButtonAdapter.setGreenSet(false);
                }
                if(!blueCorrect) {
                    ((ColorGamePanel)GUIManager.getPanel("colorgame")).setColorButtonIsEnabled("blue", true);
                    colorButtonAdapter.setBlueSet(false);
                }

            }
            else{
                StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
                StateMachine.nextState();
            }

    }
}
