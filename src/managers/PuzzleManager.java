package managers;

import data.Puzzle;
import gui.panels.PuzzlePanel;
import interfaces.Interactible;
import interfaces.ScoreModifier;
import main.ManagerHandler;
import main.StateMachine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PuzzleManager implements ScoreModifier {

    public static class AnswerButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((PuzzlePanel.PuzzleButton) e.getSource()).isEnabled()) {
                String answer = ((JButton) e.getSource()).getText();
                if (answer.equals(actualPuzzle.getCorrect_answer())) {
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


    private final AnswerButtonListener answerButtonListener = new AnswerButtonListener();

    private ArrayList<Puzzle> allQuestions = loadAllQuestions();
    private static Puzzle actualPuzzle;

    private static boolean puzzleAnsweredRight;


    public void init() {
        ((Interactible) ManagerHandler.getGUIManager().getPanel("puzzle")).addButtonListener(answerButtonListener, "0");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("puzzle")).addButtonListener(answerButtonListener, "1");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("puzzle")).addButtonListener(answerButtonListener, "2");
        ((Interactible)ManagerHandler.getGUIManager().getPanel("puzzle")).addButtonListener(answerButtonListener, "3");
    }

    public void refreshPuzzle(){
        ((PuzzlePanel)ManagerHandler.getGUIManager().getPanel("puzzle")).setAllButtonsAsInactive();
        generateRandomPuzzle();
        ArrayList<String> answers = actualPuzzle.getAnswers();
        for(int answerId = 0; answerId < answers.size(); answerId++)
        {
            ((PuzzlePanel)ManagerHandler.getGUIManager().getPanel("puzzle")).setAnswerButtonText(Integer.toString(answerId), answers.get(answerId));
        }
        ((PuzzlePanel)ManagerHandler.getGUIManager().getPanel("puzzle")).setQuestionContent(actualPuzzle.getQuestion());
    }

    public void generateRandomPuzzle()
    {
        if(allQuestions.size() == 1)
            actualPuzzle = allQuestions.get(0);
        else {
            int puzzleId = new Random().nextInt(0, allQuestions.size());
            actualPuzzle = allQuestions.get(puzzleId);
        }

        Collections.shuffle(actualPuzzle.getAnswers());

        allQuestions.remove(actualPuzzle);
        if(allQuestions.size() == 0)
            allQuestions = loadAllQuestions();
    }


    private ArrayList<Puzzle> loadAllQuestions() {

        ArrayList<Puzzle> puzzleList = new ArrayList<>();
        try {
            File questionsFile = new File("resources\\questions.txt");
            Scanner myReader = new Scanner(questionsFile);
            while (myReader.hasNextLine()) {
                String row = myReader.nextLine();
                String[] rowTable = row.split("\t");
                String question = rowTable[0];
                String[] answers = rowTable[1].replace("<", "").replace(">", "").split(";");
                int correctAnswerId = Integer.parseInt(rowTable[2]);
                Puzzle puzzle = new Puzzle();
                puzzle.setQuestion(question);
                for (String answer : answers) puzzle.addAnswer(answer);
                puzzle.setCorrect_answer(answers[correctAnswerId]);
                puzzleList.add(puzzle);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    return puzzleList;
    }

    @Override
    public int getScoreModifier() {
        if (puzzleAnsweredRight)
            return actualPuzzle.getScoreModifier();
        else
            return -10;
    }


}
