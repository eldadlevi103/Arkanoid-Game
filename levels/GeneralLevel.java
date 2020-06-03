package levels;

import java.util.ArrayList;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * Class GeneralLevel.
 * @author Eldad Levi 316363399
 */
public class GeneralLevel implements LevelInformation {
    private int numOfBalls;
    private ArrayList<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private ArrayList<Block> initialBlocks;
    private int numberOfBlocksToRemove;

    /**
     * @param numOfBalls1 - setting the number of balls in the level
     */
    public void setNumOfBalls(int numOfBalls1) {
        this.numOfBalls = numOfBalls1;
    }

    /**
     * @param initialBallVelocities1 - setting the velocitys of the balls in the level
     */
    public void setInitialBallVelocities(ArrayList<Velocity> initialBallVelocities1) {
        this.initialBallVelocities = initialBallVelocities1;
    }

    /**
     * @param paddleSpeed1 - setting the paddle's speed
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * @param paddleWidth1 - setting the paddles's width
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * @param levelName1 - setting the level Name
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * @param getBackground1 - setting the backGround
     */
    public void setGetBackground(Sprite getBackground1) {
        this.getBackground = getBackground1;
    }

    /**
     * @param initialBlocks1 - setting the blocks in the level
     */
    public void setInitialBlocks(ArrayList<Block> initialBlocks1) {
        this.initialBlocks = initialBlocks1;
    }

    /**
     * @param numberOfBlocksToRemove1 - setting the number of blocks we need to remove
     */
    public void setNumberOfBlocksToRemove(int numberOfBlocksToRemove1) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemove1;
    }

    @Override
    public int numberOfBalls() {

        return this.numOfBalls;
    }

    @Override
    public ArrayList<Velocity> initialBallVelocities() {

        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {

        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {

        return this.paddleWidth;
    }

    @Override
    public String levelName() {

        return this.levelName;
    }

    @Override
    public Sprite getBackground() {

        return this.getBackground;
    }

    @Override
    public ArrayList<Block> blocks() {
        return this.initialBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {

        return this.numberOfBlocksToRemove;
    }

}
