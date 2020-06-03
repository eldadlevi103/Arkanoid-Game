package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class KeyPressStoppableAnimation.
 * @author Eldad Levi 316363399
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private Animation animation;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * @param sensor    - the keyboardSensor parameter
     * @param key       - the key that has been pressed
     * @param animation - the animation we want to screen
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                this.isAlreadyPressed = false;
            }
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
