package listener;

import geometry.Ball;
import sprites.Block;

/**
 * Interface HitListener.
 * author Eldad Levi 316363399
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * the hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that being hit
     * @param hitter - the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}