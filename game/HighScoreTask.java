package game;

import java.io.File;

/**
 * Class HighScoreTask.
 * @author Eldad Levi 316363399
 */
public class HighScoreTask implements Task {
    private AnimationRunner ar;
    private MenuAnimation ma;
    private HighScoresTable highScoresTable;
    private int flag;

    /**
     * @param ar              - the animationRunner parameter
     * @param ma              - the menuAnimation parameter
     * @param highScoresTable - the highScores table
     */
    public HighScoreTask(AnimationRunner ar, MenuAnimation ma, HighScoresTable highScoresTable) {
        this.ar = ar;
        this.ma = ma;
        this.highScoresTable = highScoresTable;
        this.flag = flag;
    }

    @Override
    public Object run() {

        File filename = new File("highscores");
        HighScoresTable table = null;
        table = HighScoresTable.loadFromFile(filename);
        Animation a2 = new HighScoresAnimation(this.ar.getGui().getKeyboardSensor(), table);
        Animation a3 = new KeyPressStoppableAnimation(this.ar.getGui().getKeyboardSensor(),
                this.ar.getGui().getKeyboardSensor().SPACE_KEY, a2);
        this.ar.run(a3);
        return null;
    }

}
