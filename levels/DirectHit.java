package levels;

import java.util.ArrayList;
import java.util.List;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * Class DirectHit.
 * @author Eldad Levi 316363399
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, 5));
        return (ArrayList<Velocity>) velocities;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return (Sprite) new DirectHitBackground();
    }

    @Override
    public ArrayList<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(250, 200), 80, 80), 5));
        return (ArrayList<Block>) blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}