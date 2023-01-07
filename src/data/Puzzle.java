package data;

import interfaces.ScoreModifier;

import java.util.ArrayList;
import java.util.Random;

public class Puzzle implements ScoreModifier {
    private String question;
    private ArrayList<String> answers = new ArrayList<>();
    private String correct_answer;


    public String getQuestion() {
        return question;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    @Override
    public int getScoreModifier() {
        return 10*answers.size();
    }
}
