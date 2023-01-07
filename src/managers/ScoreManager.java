package managers;

import java.io.*;
import java.util.*;

/**
 * <p>Manager of high scores file database. I/O operations, updating scores, getting sorted scores.</p>
 * <p>Usage: loadEntries() to load scores to Map, then do some operations, and saveEntries() to update. Finally unloadEntries() to release memory.</p>
 * <p>Short usage for single entry update: updateEntryInFile()</p>**/
public class ScoreManager {
    private static final String SCORES_PATH = "scores.dat"; // TODO modify to the proper path of score assets / saves
    private static Map<String, Integer> scores = new HashMap<>();

    /** Shortcut for: load, updateEntry (1 entry), save & unload **/
    public void updateEntryInFile(String playerNickname, int score) {
        if (scores.isEmpty()) {
            loadEntries();
            updateEntry(playerNickname, score);
            saveEntries();
            unloadEntries();
        }
        else {
            throw new RuntimeException("Attempt to override non-empty score Map! " +
                    "Entries must be unloaded at first in order to prevent unexpected data loss.");
        }
    }
    public void loadEntries() {
        // auto-closeable
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SCORES_PATH))) {

            scores = (Map<String, Integer>) objectInputStream.readObject();
        }
        catch (FileNotFoundException e) {
            saveEntries(); // call saveScores to create an empty database file
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEntries() {
        // auto-closeable
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SCORES_PATH))) {

            objectOutputStream.writeObject(scores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void unloadEntries() {
        scores.clear();
    }

    public SortedSet<Map.Entry<String, Integer>> getEntriesSortedByScoreDescending() {
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

    public void updateEntry(String playerNickname, int score) {
        scores.put(playerNickname, score);
    }

    public void removeEntry(String playerNickname) {
        scores.remove(playerNickname);
    }

    public int getScoreByNickname(String playerNickname) {
        return scores.get(playerNickname);
    }

    public ScoreManager() {}
}
