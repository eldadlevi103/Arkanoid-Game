package sprites;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;

/**
 * Interface Collidable.
 * @author Eldad Levi 316363399
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  - the point where the ball hits the block
     * @param currentVelocity - the velocity when the ball hits the block
     * @param hitter - the ball that hit the block
     * @return -the new velocity expected after the hit
     */
     Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}