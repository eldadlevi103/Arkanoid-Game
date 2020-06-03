package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class AnimationRunner.
 * @author Eldad levi 316363399
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * @param frames - the frames per second value
     * @param gui1   - the GUI parameter
     */
    public AnimationRunner(int frames, GUI gui1) {
        this.framesPerSecond = frames;
        this.sleeper = new Sleeper();
        this.gui = gui1;
    }

    /**
     * will run the frames one by one. this method actually runs our game and makes
     * it look as real game and not frames.
     * @param animation - the animation we want to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return gui
     */
    public GUI getGui() {
        return this.gui;
    }

}