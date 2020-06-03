package game;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import listener.Counter;

/**
 * Class EndScreen.
 * @author Eldad Levi 316363399
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private Counter lives;
    private Image img1;
    private Image img2;

    /**
     * @param k     - the keyboardSensor parameter
     * @param score - the score the user gained
     * @param lives - the lives remaining to the user
     */
    public EndScreen(KeyboardSensor k, Counter score, Counter lives) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.lives = lives;
        ClassLoader c = ClassLoader.getSystemClassLoader();
        InputStream is = c.getResourceAsStream("sadEmoji.png");
        try {
            this.img1 = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println();
        }
        ClassLoader c2 = ClassLoader.getSystemClassLoader();
        InputStream is2 = c2.getResourceAsStream("happyface.jpg");
        try {
            this.img2 = ImageIO.read(is2);
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // in case the user lost
        if (this.lives.getValue() == 0) {

            d.drawImage(0, 0, this.img1);
            d.drawText(10, d.getHeight() / 2, "Game Over- your score is: " + this.score.getValue(), 32);

        } else { // in case the user won

            d.drawImage(0, 0, this.img2);
            d.drawText(10, d.getHeight() / 2, "You Win!- your score is: " + this.score.getValue(), 32);

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
