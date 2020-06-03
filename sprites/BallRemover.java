package sprites;

import geometry.Ball;
import listener.Counter;
import listener.HitListener;
import game.GameLevel;

/**
 * Class BallRemover.
 * @author Eldad Levi 316363399
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * @param game         - the game parameter
     * @param removedBalls - the balls that we removed
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit. the hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that being hit
     * @param hitter   - the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getForce() == -2) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}