package levels;

import java.util.ArrayList;
import geometry.Velocity;
import sprites.Sprite;

/**
 * Interface LevelInformation.
 * @author Eldad Levi 316363399
 */
public interface LevelInformation {
    /**
     * @return the number of balls we have in the game
     */
    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * The initial velocity of each ball. Note that initialBallVelocities().size()==
     * numberOfBalls().
     * @return list that holds the velocities for the balls
     */
    ArrayList<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the level's name
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and
     * location.
     * @return a list that holds the structure of the block
     */
    ArrayList blocks();

    /**
     * Number of levels that should be removed before the level is considered to be
     * "cleared". This number should be <= blocks.size();.
     * @return the number of the blocks
     */
    int numberOfBlocksToRemove();
}
