package data;

import java.util.Random;

public class Puzzle {
    private int riddle_num;
    private String riddle;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correct_answer;

    public boolean checkCorrectAnswerAvailable() {
        if (correct_answer.equals(answer1) || correct_answer.equals(answer2) || correct_answer.equals(answer3)
                || correct_answer.equals(answer4)) {
            return true;

        } else
            return false;
    }

    public String CheckCorrectAnswer() {
        if (correct_answer.equals(answer1))
            return answer1;
        else if (correct_answer.equals(answer2))
            return answer2;
        else if (correct_answer.equals(answer3))
            return answer3;
        else
            return answer4;
    }

    public String getRiddle() {
        return riddle;
    }

    public int getRiddle_num() {
        return riddle_num;
    }

    public void setRiddle_num(int riddle_num) {
        this.riddle_num = riddle_num;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

}
