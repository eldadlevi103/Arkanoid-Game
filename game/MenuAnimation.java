package game;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class MenuAnimation.
 * @author Eldad Levi 316363399
 * @param <T>
 */
public class MenuAnimation<T> implements Menu {
    private String title;
    private Map<String, Selection<T>> map;
    private Boolean stop;
    private KeyboardSensor sensor;
    private Object status;
    private Image img1;
    private Image img2;

    /**
     * @param title  -the title of the menu
     * @param sensor - the keyboardSensor parameter
     */
    public MenuAnimation(String title, KeyboardSensor sensor) {
        this.title = title;
        this.map = new HashMap<String, Selection<T>>();
        this.stop = false;
        this.sensor = sensor;
        this.status = null;

        ClassLoader c = ClassLoader.getSystemClassLoader();
        InputStream is = c.getResourceAsStream("sea.jpg");
        try {

            this.img1 = ImageIO.read(is);

        } catch (IOException e) {
            System.out.println();
        }

        ClassLoader c1 = ClassLoader.getSystemClassLoader();
        InputStream is1 = c1.getResourceAsStream("sunset.jpg");
        try {
            this.img2 = ImageIO.read(is1);
        } catch (IOException e) {
            System.out.println();
        }

    }
    @Override
    public void doOneFrame(DrawSurface d) {

        if (this.title.equals("Select Difficult")) {
            d.setColor(Color.WHITE);
            d.drawText(10, 50, this.title, 50);

            d.drawImage(0, 0, this.img1);

        } else {
            d.setColor(Color.BLACK);
            d.drawText(10, 50, this.title, 50);
            d.drawImage(0, 0, this.img2);
        }

        d.setColor(Color.WHITE);
        d.drawText(550, 550, "Made by Eldad", 30);
        int position = 20;
        for (Map.Entry<String, Selection<T>> entry : this.map.entrySet()) {
            d.drawText(100, 100 + position, entry.getValue().getMessage(), 50);
            position += 70;

            if (this.sensor.isPressed(entry.getKey())) {
                this.status = entry.getValue().getReturnVal();
                this.stop = true;

            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        T returnValCast = (T) returnVal;
        Selection selection = new Selection(key, message, returnValCast);
        this.map.put(key, selection);

    }

    @Override
    public Object getStatus() {

        return this.status;
    }

    /**
     * Changing the stop.
     */
    public void changestop() {
        this.stop = false;
    }

    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        Selection<T> selection = new Selection<T>(key, message, (T) subMenu);
        this.map.put(key, selection);

    }
}
