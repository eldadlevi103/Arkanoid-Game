package sprites;

import geometry.Ball;
import listener.Counter;
import listener.HitListener;
import game.GameLevel;

/**
 * Class BlockRemover.
 * @author Eldad Levi 316363399
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * @param game          - the game parameter
     * @param removedBlocks - the block that have been removed
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * This method is called whenever the beingHit object is hit. the hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that being hit
     * @param hitter   - the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getForce() == 0.0) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
        if (beingHit.getForce() == -2) {
            hitter.removeFromGame(this.game);

        }

    }
}