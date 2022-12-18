import java.io.Serial;
import java.io.Serializable;

// TODO method arguments validation

public class Score implements Serializable {
    private String playerNickname; // TODO Is it supposed to be here?
    private int score;

    @Serial
    private static final long serialVersionUID = -5838447731161289973L;

    public Score(String playerNickname, int score) {
        this.playerNickname = playerNickname;
        this.score = score;
    }

    public Score(String playerNickname) {
        this(playerNickname, 0);
    }

    public void increaseBy(int increaseValue) {
        score += increaseValue;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public String toString() {
        return playerNickname + "'s score: " + score;
    }
}
