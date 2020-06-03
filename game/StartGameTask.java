package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import levels.GameFlow;
import levels.LevelInformation;
import listener.Counter;

/**
 * Class StartGameTask.
 * @author Eldad Levi 316363399
 */
public class StartGameTask implements Task {
    private AnimationRunner ar;
    private MenuAnimation ma;
    private Counter lives;
    private Counter score;
    private HighScoresTable hst;
    private ArrayList<LevelInformation> levelsList;
    private String textDefintion;

    /**
     * @param runner          - the animationRunner parameter
     * @param menuAnimation   - the menuAnimation parameter
     * @param lives           - the lives of the player
     * @param score           - the score of the player
     * @param highScoresTable - the high scores table
     * @param levelsList      - the list that holds the levels
     * @param value           - the text we need for the level
     */
    public StartGameTask(AnimationRunner runner, MenuAnimation menuAnimation, Counter lives, Counter score,
            HighScoresTable highScoresTable, ArrayList<LevelInformation> levelsList, String value) {
        this.ar = runner;
        this.ma = menuAnimation;
        this.lives = lives;
        this.score = score;
        this.hst = highScoresTable;
        this.levelsList = levelsList;
        this.textDefintion = value;
    }

    @Override
    public Object run() {
        // loading the high score table
        try {
            this.hst.load(new File("highscores"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LevelSpecificationReader lsfr = new LevelSpecificationReader();

        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(this.textDefintion)));
        this.levelsList = (ArrayList<LevelInformation>) lsfr.fromReader(fileReader);

        // initializing the game flow and running the game
        GameFlow gameFlow = new GameFlow(this.ar, this.ar.getGui().getKeyboardSensor(), this.ar.getGui(), this.lives,
                this.score, this.hst);
        gameFlow.runLevels(this.levelsList);
        this.ma.changestop();
        this.ar.run(this.ma);
        return null;
    }
}
