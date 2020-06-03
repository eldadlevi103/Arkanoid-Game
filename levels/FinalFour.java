package levels;

import java.util.ArrayList;
import java.util.List;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * Class FinalFour.
 * @author Eldad Levi 316363399
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocities.add(new Velocity(3, 1 + i));
        }
        return (ArrayList<Velocity>) velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return (Sprite) new FinalFourBackground();
    }

    @Override
    public ArrayList<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int positionBlockY = 200;
        int size = 14;
        int range = 40;

        for (int i = 0; i < 7; i++) {
            int positionBlockX = 15;
            for (int j = 0; j < size; j++) {
                Rectangle rect = new Rectangle(new Point(positionBlockX, positionBlockY), 48, 15);
                if (i == 0) {
                    Block block = new Block(rect, 2);
                    blocks.add(block);

                } else {
                    Block block = new Block(rect, 1);
                    blocks.add(block);
                }
                positionBlockX += 40;
            }

            positionBlockY = positionBlockY + 15;
            range = range + 40;
        }
        return (ArrayList<Block>) blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}