import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.HighScoreTask;
import game.HighScoresTable;
import game.MenuAnimation;
import game.QuitTask;
import game.StartGameTask;
import game.SubMenuTask;
import game.Task;
import levels.LevelInformation;
import listener.Counter;

/**
 * Class Ass7Game.
 * @author Eldad Levi 316363399
 */
public class Ass7Game {
    /**
     * the main- sets a new game, initialize the sprites and runs the game.
     * @param args - arguments from the cmd- will determine which levels to run.
     */
    public static void main(String[] args) {
        // creating list to hold the levels
        ArrayList<LevelInformation> levelsList = new ArrayList<>();
        Map<String, String> levelDiff = new HashMap<String, String>();

        // creating new high Score Table
        HighScoresTable highScoresTable = new HighScoresTable(5);

        // setting the frame
        GUI gui = new GUI("Brick Breaker", 800, 600);
        // Initializing the keyboard
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        // creating lives
        Counter lives = new Counter();
        lives.increase(3);

        // creating score
        Counter score = new Counter();
        score.increase(0);

        // Initializing the animationRunner
        AnimationRunner runner = new AnimationRunner(60, gui);

        // Initializing the menu Animation Runner
        MenuAnimation menuAnimation = new MenuAnimation("Arkanoid", keyboard);

        // creating the Quit Task and the highScore Task
        QuitTask quitTask = new QuitTask(runner, menuAnimation);
        HighScoreTask highScoreTask = new HighScoreTask(runner, menuAnimation, highScoresTable);

        // Initializing the subMenu Animation Runner
        MenuAnimation<String> subMenuDifficult = new MenuAnimation("Select Difficult", keyboard);

        // creating the subMenu Task and the highScore Task
        SubMenuTask subMenuTask = new SubMenuTask(runner, subMenuDifficult, gui);

        // adding the selections to the menu
        menuAnimation.addSelection("s", "(S).Start the Game", subMenuTask);
        menuAnimation.addSelection("h", "(H). High Scores", highScoreTask);
        menuAnimation.addSelection("q", "(Q). Quit Game", quitTask);

        // reading from the cmd the level sets file, if needed
        String filePath;
        if (args.length == 0) {

            filePath = "level_sets.txt";
        } else {
            filePath = (args[0]);
        }
        String line;
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(filePath)));

        // putting in the map the informations about the level sets
        try {
            while ((line = fileReader.readLine()) != null) {
                String firstLine = line;
                line = fileReader.readLine();
                String secondLine = line;
                levelDiff.put(firstLine, secondLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // creating the subMenu according to the information
        for (Map.Entry<String, String> entry : levelDiff.entrySet()) {
            StartGameTask startGameTask = new StartGameTask(runner, menuAnimation, lives, score, highScoresTable,
                    levelsList, entry.getValue());
            line = entry.getKey();
            String[] str = line.split(":");
            subMenuDifficult.addSelection(str[0], str[1], startGameTask);

        }
        while (true) { // running the menu
            runner.run(menuAnimation);
            Task<Void> task = (Task<Void>) menuAnimation.getStatus();
            task.run();
            menuAnimation.changestop();
            lives.setValue(3);
            score.setValue(0);
            highScoresTable.clear();
            subMenuDifficult.changestop();

        }
    }
}