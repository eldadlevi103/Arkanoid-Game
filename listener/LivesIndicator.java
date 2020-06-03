package listener;

import java.awt.Color;
import sprites.Sprite;
import biuoop.DrawSurface;

/**
 * Class LivesIndicator.
 * @author Eldad Levi 316363399
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * @param lives - the lives the player have
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * @param d - the drawsurace function's parameter
     */
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.drawText(40, 15, this.toString(), 20);
    }
    /**
     * not used.
     */
    public void timePassed() {
    }
    /**
     * @return - nice string that shows the lives of the player.
     */
    public String toString() {
        return "lives:  " + this.lives.getValue();
    }

}