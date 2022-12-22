import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String SCORES_PATH = "scores.dat"; // TODO modify to the proper path of score assets / saves
    private static Map<String, Integer> scores = new HashMap<>();


    public static void loadEntries() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SCORES_PATH));

            scores = (Map<String, Integer>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (FileNotFoundException e) {
            saveEntries(); // call saveScores to create an empty database file
        }
    }

    public static void saveEntries() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SCORES_PATH));

        objectOutputStream.writeObject(scores);
        objectOutputStream.close();
    }

    public static SortedSet<Map.Entry<String, Integer>> getEntriesSortedByScoreDescending() {
        // creates an empty TreeSet with a rule that new entries will be inserted in a sorted way, score descending
        SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(
                new Comparator<>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        if (o1.getValue() < o2.getValue()) return 1;
                        else return -1;
                    }
                }
        );

        sortedEntries.addAll(scores.entrySet());
        return sortedEntries;
    }

    public static void addEntry(String playerNickname, int score) {
        scores.put(playerNickname, score);
    }

    public static void removeEntry(String playerNickname) {
        scores.remove(playerNickname);
    }

    public static void unloadEntries() {
        scores.clear();
    }

    public static int getScoreByNickname(String playerNickname) {
        return scores.get(playerNickname);
    }

    private ScoreManager() {} // Utility class = can't create objects
}
