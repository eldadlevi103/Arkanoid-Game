package listener;

import geometry.Ball;
import sprites.Block;

/**
 * Class ScoreTrackingListener.
 * @author Eldad Levi 316363399
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * @param scoreCounter - the score counter that indicate the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit. the hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that being hit
     * @param hitter   - the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getForce() > 0.0) {
            currentScore.increase(5);
        }
        if (beingHit.getForce() == 0.0) {
            currentScore.increase(10);
        }
    }

    /**
     * @return the current score.
     */
    public Counter getValueOfScore() {
        return this.currentScore;
    }
}