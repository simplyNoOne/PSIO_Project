import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO method arguments validation
public final class FileManager { // class is static so it's final (= cannot be extended)
    private static final String SCORES_PATH = "scores.dat"; // TODO modify to the proper path of score assets / saves
    private static Map<String, Integer> scoresMap = new HashMap<>(); // TODO TreeMap for better performance? Doesn't really matter I think

    private FileManager() {} // static class

    /** Adds a player's score (to RAM). If it's a new entry for the given nickname, it's creates one. Else, it updates the entry. Need to call saveScores() to save the change to disk. **/
    public static void addScore(Score score) {
        scoresMap.put(score.getPlayerNickname(), score.getScore());
    }

    /** Returns the score of a given player searched by nickname. Currently, if there's no such nickname, NullPointerException is thrown **/
    public static Score getScoreByNickname(String nickname) {
        int rawScore = scoresMap.get(nickname);
        return new Score(nickname, rawScore);
    }

    /** Removes the score of a given player. Currently, if there isn't such score, method has no effect. **/
    public static void removeScoreByNickname(String nickname) {
        scoresMap.remove(nickname);
    }

    /** Loads all scores (to RAM). They override all currently remembered scores. If there is no database file, it creates one. **/
    public static void loadScores() throws IOException, ClassNotFoundException {
        scoresMap.clear();

        // TODO dunno how to code this better; no way to check if there are still objects to read so I just catch EndOfFile Error
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SCORES_PATH));

            while (true) {
                Score score = (Score) objectInputStream.readObject();
                scoresMap.put(score.getPlayerNickname(), score.getScore());
            }
            // current implementation of loadScores prevents from manually putting here ...stream.close(); but as it's AutoCloseable, it's not a problem
        }
        catch (FileNotFoundException e) {
            saveScores(); // call saveScores to create an empty database file
        }
        catch (EOFException e) {
            // end of reading file
        }
    }

    /** Saves scores to disk as serialized Score objects. Overrides scores previously stored on the disk. **/
    public static void saveScores() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SCORES_PATH));

        for (Map.Entry<String, Integer> scoreEntry : scoresMap.entrySet()) {
            Score score = new Score(scoreEntry.getKey(), scoreEntry.getValue());
            objectOutputStream.writeObject(score);
        }

        objectOutputStream.close();
    }
}
