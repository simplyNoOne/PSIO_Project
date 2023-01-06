package managers;

import data.Quiz;
import gui.panels.ColorGamePanel;
import gui.panels.QuizPanel;
import interfaces.Interactible;
import main.StateMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static class ColorButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //MouseInfo.getPointerInfo().getLocation().y;
            System.out.println(MouseInfo.getPointerInfo().getLocation().y);
            System.out.println(((JButton)e.getSource()).getY());
        }
    }


    private static final QuizAnswerButtonListener quizAnswerButtonListener = new QuizAnswerButtonListener();

    private static ArrayList<Quiz> allQuestions = loadAllQuestions();
    private static Quiz actualQuiz;


    public static void init() {
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "0");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "1");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "2");
        ((Interactible)GUIManager.getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "3");
    }

    public static void newPuzzle(){

        if(new Random().nextInt(0, 2) == 0){
            GUIManager.addPanel("quiz", "game");
            GUIManager.removePanel("colorgame", "game");
            refreshQuiz();
            GUIManager.getPane("game").repaint();
        }
        else {
            GUIManager.removePanel("quiz", "game");
            GUIManager.addPanel("colorgame", "game");
            refreshColorGame();
            GUIManager.getPane("game").repaint();
        }
    }

    public static void refreshColorGame(){
        int red = new Random().nextInt(0, 256);
        int green = new Random().nextInt(0, 256);
        int blue = new Random().nextInt(0, 256);
        ((ColorGamePanel)GUIManager.getPanel("colorgame")).setExpectedColor(new Color(red, green, blue));
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



}
