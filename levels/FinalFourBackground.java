package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * Class FinalFourBackground. in this class we will draw the background of the
 * specific level.
 * @author Eldad Levi 316363399
 */
public class FinalFourBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 15);

        d.setColor(Color.GRAY);
        d.fillCircle(100, 400, 35);
        d.fillCircle(130, 400, 40);
        d.fillCircle(120, 420, 35);
        d.fillCircle(150, 380, 40);
        d.fillCircle(180, 400, 40);

        d.fillCircle(400, 400, 35);
        d.fillCircle(430, 400, 40);
        d.fillCircle(420, 420, 35);
        d.fillCircle(450, 380, 40);
        d.fillCircle(480, 400, 40);

        d.setColor(Color.WHITE);
        d.drawLine(100, 445, 100, 600);
        d.drawLine(110, 445, 110, 600);
        d.drawLine(120, 445, 120, 600);
        d.drawLine(130, 445, 130, 600);
        d.drawLine(140, 445, 140, 600);
        d.drawLine(150, 435, 150, 600);
        d.drawLine(160, 435, 160, 600);
        d.drawLine(170, 435, 170, 600);
        d.drawLine(180, 435, 180, 600);
        d.drawLine(190, 435, 190, 600);

        d.drawLine(400, 445, 400, 600);
        d.drawLine(410, 445, 410, 600);
        d.drawLine(420, 445, 420, 600);
        d.drawLine(430, 445, 430, 600);
        d.drawLine(440, 445, 440, 600);
        d.drawLine(450, 435, 450, 600);
        d.drawLine(460, 435, 460, 600);
        d.drawLine(470, 435, 470, 600);
        d.drawLine(480, 435, 480, 600);
        d.drawLine(490, 435, 490, 600);

    }

    @Override
    public void timePassed() {

    }

}
