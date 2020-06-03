package sprites;
import geometry.Point;

/**
 * Class CollisionInfo.
 * @author Eldad Levi 316363399
 *
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collsionObject;

    /**
     * gives information about the collision.
     * @param collisionPoint - the point where the ball hits the block
     * @param collsionObject - the object- the block that the ball collide with
     */
    public CollisionInfo(Point collisionPoint, Collidable collsionObject) {
        this.collisionPoint = collisionPoint;
        this.collsionObject = collsionObject;
    }
    /**
     * @return -the point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @return -the collidable object involved in the collision
     */
    public Collidable getCollisionObject() {
        return this.collsionObject;

    }
}