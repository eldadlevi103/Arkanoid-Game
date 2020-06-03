package listener;

import sprites.Sprite;
import java.awt.Color;
import biuoop.DrawSurface;

/**
 * Class ScoreIndicator.
 * @author Eldad Levi 316363399
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * @param score - the score that the player gains
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * @param d - the drawsurace function's parameter
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 15);
        d.setColor(Color.BLACK);
        d.drawText(350, 15, this.toString(), 25);

    }

    /**
     * not used.
     */
    public void timePassed() {
    }

    /**
     * @return - nice string that shows the score of the player.
     */
    public String toString() {
        return "score:  " + this.score.getValue();
    }

}
