package UnitTests;
import managers.PuzzleManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class PuzzleManagerTest {
    Random randomGenerator = new Random();
    @Test
    void getScoreModifier()
    {
            int expectedScore = 0;
            PuzzleManager testPuzzleManager = new PuzzleManager();
            testPuzzleManager.setPuzzleAnsweredRight(randomGenerator.nextBoolean());
            if (testPuzzleManager.isPuzzleAnsweredRight())
            {
                switch (randomGenerator.nextInt(0, 2))
                {
                    case 0 ->
                    {
                        testPuzzleManager.setPuzzleType("quiz");
                        testPuzzleManager.generateRandomQuiz();
                        expectedScore = 100 + 10 * testPuzzleManager.getActualQuiz().getAnswers().size();
                    }
                    case 1 ->
                    {
                        testPuzzleManager.setLeftChanceNumber(randomGenerator.nextInt(0, 6));
                        testPuzzleManager.setPuzzleType("colorgame");
                        expectedScore = 100 + 10 * testPuzzleManager.getLeftChanceNumber();
                    }
                }
            }
            else
            {
                expectedScore = -100;
            }
            Assertions.assertEquals(expectedScore, testPuzzleManager.getScoreModifier());
    }

}