package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * Class WideEasyBackground.
 * @author Eldad Levi 316363399
 */
public class WideEasyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 15);

        d.setColor(Color.ORANGE);
        d.fillCircle(100, 100, 80);

        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 60);
        int temp = 0;
        for (int i = 0; i < 100; i++) {
            d.drawLine(100, 100, 15 + temp, 200);
            temp += 6;
        }
    }

    @Override
    public void timePassed() {

    }

}
