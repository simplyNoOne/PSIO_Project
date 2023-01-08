package managers;

import data.Quiz;
import gui.panels.ColorGamePanel;
import gui.panels.QuizPanel;
import interfaces.Interactible;
import interfaces.ScoreModifier;
import main.ManagerHandler;
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

public class PuzzleManager implements ScoreModifier {

    public static class QuizAnswerButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((QuizPanel.QuizButton) e.getSource()).isEnabled()) {
                String answer = ((JButton) e.getSource()).getText();
                if (answer.equals(actualQuiz.getCorrect_answer())){
                    StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
                    puzzleAnsweredRight = true;
                }
                else {
                    StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
                    puzzleAnsweredRight = false;
                }

                StateMachine.nextState();
            }
        }
    }

    public static class MouseAdapterForColorGame extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (((JButton) e.getSource()).isEnabled())
                ((ColorGamePanel) GUIManager.getPanel("colorgame")).showValueOnButton(((JButton)e.getSource()).getName(), ""+(255-e.getY()));
        }

    }


    private static final QuizAnswerButtonListener quizAnswerButtonListener = new QuizAnswerButtonListener();
    private static final MouseAdapterForColorGame colorButtonAdapter = new MouseAdapterForColorGame();

    private ArrayList<Quiz> allQuestions = loadAllQuestions();
    private static Quiz actualQuiz;
    private static String puzzleType;

    private static boolean puzzleAnsweredRight;

    public static void init() {
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "0");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "1");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "2");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("quiz")).addButtonListener(quizAnswerButtonListener, "3");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addMouseListener(colorButtonAdapter, "red");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addMouseListener(colorButtonAdapter, "green");
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).addMouseListener(colorButtonAdapter, "blue");
    }

    public static void newPuzzle(){

        if(new Random().nextInt(0, 2) == 0){
            ManagerHandler.getGUIManager().addPanel("quiz", "game");
            refreshQuiz();
            ManagerHandler.getGUIManager().getPane("game").repaint();
            puzzleType = "quiz";
        }
        else {
            ManagerHandler.getGUIManager().addPanel("colorgame", "game");
            refreshColorGame();
            ManagerHandler.getGUIManager().getPane("game").repaint();
            puzzleType = "colorgame";
        }
    }

    public static void refreshColorGame(){
        int red = new Random().nextInt(0, 256);
        int green = new Random().nextInt(0, 256);
        int blue = new Random().nextInt(0, 256);
        ((ColorGamePanel)ManagerHandler.getGUIManager().getPanel("colorgame")).setExpectedColor(new Color(red, green, blue));
    }

    public static void refreshQuiz(){
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


    private  ArrayList<Quiz> loadAllQuestions() {

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

    public static String getPuzzleType() {
        return puzzleType;
    }
    @Override
    public int getScoreModifier() {
        if (puzzleAnsweredRight)
            return actualPuzzle.getScoreModifier();
        else
            return -10;
    }


}
