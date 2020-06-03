package game;

/**
 * Class ScoreInfo.
 * @author Eldad Levi 316363399
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * @param name  - the name of the player
     * @param score - the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return this.name;

    }

    /**
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }

}
