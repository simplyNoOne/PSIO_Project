package managers;

import data.Puzzle;
import gui.panels.PuzzlePanel;
import interfaces.Interactible;
import main.StateMachine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class PuzzleManager {



//    public static class CorrectSolutionButtonListener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
//            StateMachine.nextState();
//        }
//    }

//    public static class WrongSolutionButtonListener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);
//            StateMachine.nextState();
//        }
//    }

    public static class AnswerButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonId = ((JButton)e.getSource()).getName();
            if(buttonId.equals(correctAnswer))
                StateMachine.setNextStateVar(StateMachine.State.SCROLL_BG);
            else
                StateMachine.setNextStateVar(StateMachine.State.PREFIGHT);

            StateMachine.nextState();



        }
    }




    private static final AnswerButtonListener answerButtonListener = new AnswerButtonListener();

    private static  ArrayList<Puzzle> allQuestions = loadAllQuestions();
    private static String correctAnswer;


    public static void init() {
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(answerButtonListener, "0");
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(answerButtonListener, "1");
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(answerButtonListener, "2");
        ((Interactible)GUIManager.getPanel("puzzle")).addButtonListener(answerButtonListener, "3");
    }

    public static void refreshPuzzle(){
        String question = "pytanie";
        String answerA = "A";
        String answerB = "B";
        String answerC = "C";
        String answerD = "D";
        ((PuzzlePanel)GUIManager.getPanel("puzzle")).setAnswerButtonText("0", answerA);
        ((PuzzlePanel)GUIManager.getPanel("puzzle")).setAnswerButtonText("1", answerB);
        ((PuzzlePanel)GUIManager.getPanel("puzzle")).setAnswerButtonText("2", answerC);
        ((PuzzlePanel)GUIManager.getPanel("puzzle")).setAnswerButtonText("3", answerD);
        ((PuzzlePanel)GUIManager.getPanel("puzzle")).setQuestionContent(question);
        correctAnswer = "0";
    }


    private static ArrayList<Puzzle> loadAllQuestions() {
        try {
            FileInputStream fos = new FileInputStream("riddles.txt");
            ObjectInputStream out = new ObjectInputStream(fos);

            out.close();
            fos.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }



}
