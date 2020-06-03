package levels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.EndScreen;
import game.KeyPressStoppableAnimation;
import game.ScoreInfo;
import game.Animation;
import game.GameLevel;
import game.HighScoresAnimation;
import game.HighScoresTable;
import listener.Counter;

/**
 * Class GameFlow.
 * @author Eldad Levi 316363399
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;
    private Counter lives;
    private Counter score;
    private GUI gui;
    private File file;
    private HighScoresTable highScoresTable = new HighScoresTable(3);

    /**
     * @param ar              - the animationRunner parameter
     * @param ks              - the keyboardSensor parameter
     * @param gui             - the GUI parameter
     * @param lives           - the lives the user have
     * @param score           - the score the user gained
     * @param highScoresTable - the highScore table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter lives, Counter score,
            HighScoresTable highScoresTable) {
        this.keyboard = ks;
        this.animationRunner = ar;
        this.gui = gui;
        this.lives = lives;
        this.score = score;
        this.highScoresTable = highScoresTable;

    }

    /**
     * @param levels - list that holds that level we will run in the order expected
     */
    public void runLevels(ArrayList<LevelInformation> levels) {
        // checking if the file exists
        File filename = new File("highscores");

        for (int i = 0; i < levels.size(); i++) {

            GameLevel level = new GameLevel(levels.get(i), this.gui, this.animationRunner, this.lives, this.score);

            level.initialize();
            // while the player still have lives and there are still block in the level-
            // keep going
            while ((this.lives.getValue() != 0) && (level.blocksLeft())) {
                level.playOneTurn();
            }
            // if the user lost all his lives= he lost the game
            if (this.lives.getValue() == 0) {
                Animation a = new EndScreen(this.keyboard, this.score, this.lives);
                Animation a1 = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, a);
                this.animationRunner.run(a1);

                // adding to the high scores table
                DialogManager dialog = this.gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                ScoreInfo scoreinfo = new ScoreInfo(name, this.score.getValue());

                this.highScoresTable.add(scoreinfo);

                if (!filename.exists()) {
                    try {
                        filename.createNewFile();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                } else {
                    try {
                        this.highScoresTable.save(filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Animation a2 = new HighScoresAnimation(this.keyboard, this.highScoresTable);
                Animation a3 = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, a2);

                this.animationRunner.run(a3);
                return;

            }

            // lose ^^
            //////////////////////////////////////////////////////////////////////////////////
            // win vv

            // if the user finished all the blocks and it was the last level in the list=
            // he won the game
            if (!level.blocksLeft() && i + 1 == levels.size()) {
                Animation a = new EndScreen(this.keyboard, this.score, this.lives);
                Animation a1 = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, a);
                this.animationRunner.run(a1);

                // adding to the high scores table
                DialogManager dialog = this.gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                ScoreInfo scoreinfo = new ScoreInfo(name, this.score.getValue());

                this.highScoresTable.add(scoreinfo);

                if (!filename.exists()) {
                    try {
                        filename.createNewFile();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                } else {
                    try {
                        this.highScoresTable.save(filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Animation a2 = new HighScoresAnimation(this.keyboard, this.highScoresTable);
                Animation a3 = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, a2);

                this.animationRunner.run(a3);
                return;
            }

        }
    }
}
