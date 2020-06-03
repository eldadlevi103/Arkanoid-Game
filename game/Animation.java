package game;

import biuoop.DrawSurface;

/**
 * Interface Animation.
 * @author Eldad levi 316363399
 */
public interface Animation {
/**
 * the method will make a frame- draw a frame to the screen.
 * @param d - the DrawSurface paramter
 */
    void doOneFrame(DrawSurface d);
/**
 * will determine when to stop the process of transmitting frames.
 * @return true or false
 */
    boolean shouldStop();
}