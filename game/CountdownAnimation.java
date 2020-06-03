package game;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import levels.LevelInformation;
import sprites.SpriteCollection;

/**
 * Class CountdownAnimation.
 * @author Eldad Levi 316363399
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int temp;
    private int flag = 0;
    private LevelInformation levelInfo;

    /**
     * @param numOfSeconds - how many seconds we delay
     * @param countFrom    - the number we start decreasing from
     * @param gameScreen   - the screen that we show- the frame
     * @param levelInfo    - the list that holds the information about the levels
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
            LevelInformation levelInfo) {
        this.numOfSeconds = numOfSeconds; // 2
        this.countFrom = countFrom; // 3
        this.gameScreen = gameScreen;
        this.temp = countFrom; // 3
        this.levelInfo = levelInfo;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.gameScreen.drawAllOn(d);

        d.setColor(Color.black);
        d.drawText(600, 15, this.levelInfo.levelName(), 20);
        d.setColor(Color.YELLOW);
        d.drawText(370, 100, String.valueOf(this.countFrom), 100);

        this.countFrom--;
        if (flag != 0) {
            Sleeper sl = new Sleeper();
            sl.sleepFor(1000 * (long) numOfSeconds / (long) this.temp);
        }
        flag = 1;
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom == -1) {
            return true;
        }
        return false;
    }

}
