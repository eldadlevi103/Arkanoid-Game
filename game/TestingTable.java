package game;

import java.io.File;
import java.io.IOException;

/**
 * Class TestingTable using this to test my highScores table.
 * @author Eldad Levi 316363399
 */
public class TestingTable {
    /**
     * @param args - the arguments from the cmd
     */
    public static void main(String[] args) {
        HighScoresTable hst = new HighScoresTable(3);

        ScoreInfo scoreinfo3 = new ScoreInfo("Yossi", 400);
        hst.add(scoreinfo3);
        ScoreInfo scoreinfo1 = new ScoreInfo("Eldad", 150);
        hst.add(scoreinfo1);
        ScoreInfo scoreinfo2 = new ScoreInfo("Guy", 0);
        hst.add(scoreinfo2);

        hst.printing();

        File filename = new File("highscores");
        try {
            hst.save(filename);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
