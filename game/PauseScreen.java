package game;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class PauseScreen.
 * @author Eldad Levi 316363399
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Image img;

    /**
     * @param k the keyboardSeonsor parameter
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        ClassLoader c = ClassLoader.getSystemClassLoader();
        InputStream is = c.getResourceAsStream("pause.jpg");
        try {
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.drawImage(0, 0, this.img);
        d.setColor(Color.BLACK);
        d.drawText(10, 40, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
