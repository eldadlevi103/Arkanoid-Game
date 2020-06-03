package levels;

import java.util.ArrayList;
import java.util.List;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * Class WideEasy.
 * @author Eldad Levi 316363399
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<Velocity>();
        list.add(Velocity.fromAngleAndSpeed(5, 6));
        list.add(Velocity.fromAngleAndSpeed(15, 6));
        list.add(Velocity.fromAngleAndSpeed(25, 6));
        list.add(Velocity.fromAngleAndSpeed(35, 6));
        list.add(Velocity.fromAngleAndSpeed(45, 6));
        list.add(Velocity.fromAngleAndSpeed(355, 6));
        list.add(Velocity.fromAngleAndSpeed(345, 6));
        list.add(Velocity.fromAngleAndSpeed(335, 6));
        list.add(Velocity.fromAngleAndSpeed(325, 6));
        list.add(Velocity.fromAngleAndSpeed(315, 6));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 300;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return (Sprite) new WideEasyBackground();
    }

    @Override
    public ArrayList<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < 15; i++) {
            blocks.add(new Block(new Rectangle(new Point(15 + temp, 200), 38, 30), 1));
            temp += 38;
        }
        return (ArrayList<Block>) blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}