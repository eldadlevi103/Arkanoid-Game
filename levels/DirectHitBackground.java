package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * Class DirectHitBackground. in this class we will draw the background of the
 * specific level.
 * @author Eldad Levi 316363399
 */
public class DirectHitBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 15);
        d.setColor(Color.BLUE);
        d.drawCircle(280, 250, 240);
        d.drawCircle(280, 250, 200);
        d.drawCircle(280, 250, 160);
        d.drawCircle(280, 250, 120);
        d.drawLine(290, 20, 290, 500);
        d.drawLine(30, 240, 530, 240);

    }

    @Override
    public void timePassed() {

    }

}
