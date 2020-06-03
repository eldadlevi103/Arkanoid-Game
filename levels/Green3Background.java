package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * Class Green3. in this class we will draw the background of the specific
 * level.
 * @author Eldad Levi 316363399
 */
public class Green3Background implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 15);

        d.setColor(Color.WHITE);
        d.fillRectangle(50, 400, 150, 200);
        d.setColor(Color.BLACK);
        d.fillRectangle(40, 400, 10, 200);
        d.fillRectangle(80, 400, 10, 200);
        d.fillRectangle(120, 400, 10, 200);
        d.fillRectangle(160, 400, 10, 200);
        d.fillRectangle(200, 400, 10, 200);
        d.fillRectangle(50, 400, 150, 10);
        d.fillRectangle(50, 440, 150, 10);
        d.fillRectangle(50, 480, 150, 10);
        d.fillRectangle(50, 520, 150, 10);
        d.fillRectangle(50, 560, 150, 10);
        d.fillRectangle(50, 590, 150, 10);
        d.fillRectangle(95, 350, 60, 50);
        d.fillRectangle(105, 300, 20, 50);
        d.setColor(Color.ORANGE);
        d.fillCircle(115, 280, 20);
        d.setColor(Color.RED);
        d.fillCircle(115, 280, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 280, 5);



    }

    @Override
    public void timePassed() {

    }

}
