package game;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import geometry.Velocity;
import levels.GeneralLevel;
import levels.GeneralLevelBackGround;
import levels.LevelInformation;
import sprites.Block;
import sprites.Sprite;

/**
 * Class LevelSpecificationReader.
 * @author Eldad Levi 316363399
 */
public class LevelSpecificationReader {
    /**
     * @param reader - the file we read from
     * @return - a list with the levels that was in the file
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        ArrayList<LevelInformation> levelsList = new ArrayList<LevelInformation>(); // the levels list
        Scanner input = new Scanner(reader); // for reading the file
        String line;
        GeneralLevel generalLevel = new GeneralLevel(); // making new general level
        int flag = 1;
        ArrayList<Block> blocksList;
        while (input.hasNextLine() && flag == 1) {
            blocksList = new ArrayList<Block>();
            line = input.nextLine();
            // statring reading the parameters for new level
            if (line.equals("START_LEVEL")) {

                line = input.nextLine(); // reading another line
                String name = line.substring(11);
                generalLevel.setLevelName(name); // setting the level name

                line = input.nextLine(); // reading another line
                ArrayList<Velocity> velocities = new ArrayList<>();
                velocities = getBallsVelocitysFromText(line);
                generalLevel.setInitialBallVelocities(velocities); // setting the velocities

                int numOfBalls = getBallsVelocitysFromText(line).size();
                generalLevel.setNumOfBalls(numOfBalls);

                line = input.nextLine(); // reading another line
                generalLevel.setGetBackground(getBackGroundFromText(line));

                line = input.nextLine(); // reading another line
                int speed = Integer.parseInt(line.substring(13)) / 100;
                generalLevel.setPaddleSpeed(speed); // setting the paddleSpeed

                line = input.nextLine(); // reading another line
                int width = Integer.parseInt(line.substring(13));
                generalLevel.setPaddleWidth(width); // setting the paddleWidth

                // Blocks section

                line = input.nextLine();
                String blockDefintions = line.substring(30);

                BlocksFromSymbolsFactory factory = createFactory(blockDefintions);

                line = input.nextLine();
                int blocksStartX = Integer.parseInt(line.substring(15));
                int tempBlocksStartX = blocksStartX;
                line = input.nextLine();
                int blocksStartY = Integer.parseInt(line.substring(15));

                line = input.nextLine();
                int rowHeight = Integer.parseInt(line.substring(11));

                line = input.nextLine();
                int numOfBlocks = Integer.parseInt(line.substring(11));
                generalLevel.setNumberOfBlocksToRemove(numOfBlocks);

                // making the blocks list

                line = input.nextLine(); // START_BLOCKS
                line = input.nextLine();

                while (!line.equals("END_BLOCKS")) {

                    String[] row = line.split("");
                    for (String symbol : row) {
                        if (factory.isSpaceSymbol(symbol)) { // if is spacer
                            tempBlocksStartX += factory.getSpaceWidth(symbol);

                        } else if (factory.isBlockSymbol(symbol)) { // if is block
                            Block b = factory.getBlock(symbol, tempBlocksStartX, blocksStartY);
                            blocksList.add(b);
                            tempBlocksStartX += b.getCollisionRectangle().getWidth();
                        }
                    }
                    tempBlocksStartX = blocksStartX;
                    blocksStartY += rowHeight;
                    line = input.nextLine(); // Continuing the while loop until it gets to END_BLOCKS
                }
                generalLevel.setInitialBlocks(blocksList);
                line = input.nextLine(); // END_LEVEL
                if (line.equals("END_LEVEL")) {
                    levelsList.add(generalLevel);
                    generalLevel = new GeneralLevel();
                    flag = 1;
                }
            }

        }
        return levelsList;
    }

    /**
     * @param line - the line that holds the velocitys
     * @return - a list that holds the velocitys
     */
    public ArrayList getBallsVelocitysFromText(String line) {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Velocity v = null;

        int angel;
        int speed;
        line = line.substring(16);
        String[] velArray = line.split(" ");
        for (String s : velArray) {
            String[] data = s.split(",");
            angel = Integer.parseInt(data[0]);
            speed = Integer.parseInt(data[1]) / 100;
            Velocity v1 = v.fromAngleAndSpeed(angel, speed);
            velocities.add(v1);
        }
        return velocities;
    }

    /**
     * @param line - the line that defines us the background
     * @return - the generalLevelBackGround
     */
    public Sprite getBackGroundFromText(String line) {
        String str;
        str = line.substring(11, line.length() - 1);
        GeneralLevelBackGround glb = new GeneralLevelBackGround(str, 600, 800);
        return glb;
    }

    /**
     * @param line - the line that defines us the blocks definitions
     * @return - factory parameter, will hold lists for blocks and spaces
     */
    public BlocksFromSymbolsFactory createFactory(String line) {
        BufferedReader blockfileReader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(line)));
        BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(blockfileReader);
        return factory;

    }
}
