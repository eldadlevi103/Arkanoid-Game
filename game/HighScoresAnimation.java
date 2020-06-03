package game;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Class HighScoresAnimation.
 * @author Eldad levi 316363399
 *
 */
public class HighScoresAnimation implements Animation {

    private Image img;
    private HighScoresTable highScoreTable;

    /**
     * @param k              - keyboardSensor parameter
     * @param highScoreTable - the highScoreTable
     */
    public HighScoresAnimation(KeyboardSensor k, HighScoresTable highScoreTable) {
        this.highScoreTable = highScoreTable;
        ClassLoader c = ClassLoader.getSystemClassLoader();
        InputStream is = c.getResourceAsStream("family.jpg");
        try {
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.drawImage(0, 0, this.img);
        d.setColor(Color.RED);
        d.drawText(130, 100, "High Scores", 100);
        int y = 200;
        for (int i = 0; i < this.highScoreTable.getHighScores().size(); i++) {
            d.drawText(110, y, (i + 1) + ", " + this.highScoreTable.getHighScores().get(i).getName(), 30);
            d.drawText(450, y, String.valueOf(this.highScoreTable.getHighScores().get(i).getScore()), 30);
            y += 25;
        }

    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}
