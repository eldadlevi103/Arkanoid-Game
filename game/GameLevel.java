package game;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import levels.LevelInformation;
import listener.Counter;
import listener.HitListener;
import listener.LivesIndicator;
import listener.ScoreIndicator;
import listener.ScoreTrackingListener;
import sprites.BallRemover;
import sprites.Block;
import sprites.BlockRemover;
import sprites.Collidable;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * Class Game.
 * @author Eldad Levi 316363399
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score = new Counter();
    private Counter lives = new Counter();
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelinfo;

    /**
     * @param levelinfo1 - the data according the the spcific level
     * @param gui        - the GUI parameter
     * @param runner     - the AnimationRunner parameter to run the level
     * @param lives      - the lives the user have
     * @param score      - the score the user gain
     */
    public GameLevel(LevelInformation levelinfo1, GUI gui, AnimationRunner runner, Counter lives, Counter score) {
        this.levelinfo = levelinfo1;
        this.gui = gui;
        this.runner = runner;
        this.lives = lives;
        this.score = score;
    }

    /**
     * adding collidables to the game.
     * @param c - the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding new sprite to the sprites list.
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them
     * to the game.
     */

    public void initialize() {

        HitListener hl = new BlockRemover(this, this.blockCounter); // removing blocks
        HitListener hl2 = new BallRemover(this, this.ballCounter); // removing balls
        HitListener hl3 = new ScoreTrackingListener(this.score); // tracking the score

        // adding the levelInfo backgrounds and details
        addSprite(this.levelinfo.getBackground());

        // Initializing the keyboard
        this.keyboard = this.gui.getKeyboardSensor();

        // adding the scoreIndicator and the livesIndicator to the game
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        addSprite(scoreIndicator);
        addSprite(livesIndicator);

        // creating the frame blocks
        Rectangle frameRect1 = new Rectangle(new Point(0, 15), 800, 15);
        Block frameBlock1 = new Block(frameRect1, 3);
        frameBlock1.addToGame(this);
        Rectangle frameRect2 = new Rectangle(new Point(0, 0), 15, 600);
        Block frameBlock2 = new Block(frameRect2, 3);
        frameBlock2.addToGame(this);
        Rectangle frameRect3 = new Rectangle(new Point(785, 0), 15, 600);
        Block frameBlock3 = new Block(frameRect3, 3);
        frameBlock3.addToGame(this);

        // death- region
        Rectangle deathRect = new Rectangle(new Point(0, 700), 800, 15);
        Block deathBlock = new Block(deathRect, -2);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(hl2);

        // setting the game blocks in the order expected to each level
        for (int i = 0; i < this.levelinfo.blocks().size(); i++) {
            Block b = (Block) this.levelinfo.blocks().get(i);
            b.addToGame(this);
            b.addHitListener(hl);
            b.addHitListener(hl3);
            this.blockCounter.increase(1);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        // making paddle and balls
        createPaddle(this.levelinfo.paddleWidth(), this.levelinfo.paddleSpeed());
        makeBalls();
        // running the animations
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.levelinfo));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * as long as the player has lives and there are remaining blocks the game will
     * go on.
     */
    public void run() {
        playOneTurn();
        while (this.lives.getValue() != 0 && this.blockCounter.getValue() != 0) {
            playOneTurn();
        }
        this.gui.close();
        return;

    }

    /**
     * @param c - the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * @param s - the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);

        d.setColor(Color.black);
        d.drawText(600, 15, this.levelinfo.levelName(), 20);

        this.sprites.notifyAllTimePassed();

        // if there are not more blocks
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // if all the balls felt
        if (this.ballCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.paddle.removeFromGame(this);
            this.running = false;
        }
        // for pausing the game
        if (this.keyboard.isPressed("p")) {
            Animation a = new PauseScreen(this.keyboard);
            Animation a1 = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, a);
            this.runner.run(a1);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @param width - the width of the paddle
     * @param speed - the speed of the paddle
     */
    public void createPaddle(int width, int speed) {
        // creating the paddle

        Rectangle rect = new Rectangle(new Point(15 + ((770 - width) / 2), 585), width, 15);
        Paddle paddle1 = new Paddle(rect, java.awt.Color.YELLOW, this.gui.getKeyboardSensor(), width, speed);
        this.paddle = paddle1;
        paddle.addToGame(this);
        // }
    }

    /**
     * @param v - the velocity of the balls we create
     */
    public void createBall(Velocity v) {
        // creating the first ball
        Ball ball1 = new Ball(400, 580, 5, java.awt.Color.BLACK);
        ball1.setGameEnvironment(this.environment);
        ball1.setOfTheFrame(new Point(16, 31), new Point(784, 1000));
        ball1.setVelocity(v);
        ball1.addToGame(this);
        this.ballCounter.increase(1);

    }

    /**
     * make as match balls at the level requires.
     */
    public void makeBalls() {
        for (int i = 0; i < this.levelinfo.initialBallVelocities().size(); i++) {
            Velocity v = this.levelinfo.initialBallVelocities().get(i);
            createBall(v);
        }
    }

    /**
     * checking if there are more blocks in the level.
     * @return true or false
     */
    public boolean blocksLeft() {
        if (this.blockCounter.getValue() != 0) {
            return true;
        }
        return false;

    }
}
