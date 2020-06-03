package game;
import java.util.List;
import geometry.Point;
import geometry.Line;
import sprites.Collidable;
import sprites.CollisionInfo;
import java.util.ArrayList;

/**
 * Class GameEnvironment.
 * @author Eldad Levi 316363399
 */

public class GameEnvironment {
   private List<Collidable> collidables;

    /**
     * creating the colldiables Arraylist.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c -the collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * remove the given collidable to the environment.
     * @param c -the collidable object
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * @param trajectory - the route the ball will move in
     * @return CollisionInfo = the information about the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Point point;
        Point closestPoint = trajectory.getEnd();
        for (Collidable c : this.collidables) {
            point = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (point != null) {
                if (trajectory.getStart().distance(point) < trajectory.getStart().distance(closestPoint)) {
                    closestCollidable = c;
                    closestPoint = point;
                }
            }
        }
        if (closestPoint.equals(trajectory.getEnd())) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
