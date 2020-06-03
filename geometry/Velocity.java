package geometry;

/**
 * Class Velocity.
 * @author Eldad Levi 316363399
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity specifies the change in position on the `x` and the `y` axes.
     * @param dx - the speed in the x direction
     * @param dy - the speed in the y direction
     */
    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle - the angle the ball will change its direction
     * @param speed - will see as the movement changes
     * @return the Velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (angle == 0) {
            double dx = 0;
            double dy = speed;
            return new Velocity(dx, dy);
        } else {
            angle = (angle + 270) % 360;
            // using geometry rules to create the new velocity
            double dx = speed * Math.cos(Math.toRadians(angle));
            double dy = speed * Math.sin(Math.toRadians(angle));
            return new Velocity(dx, dy);
        }
    }

    /**
     * @return dx - the change in movement of the ball in the x direction
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy - the change in movement of the ball in the y direction
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point. with position (x+dx,
     * y+dy).
     * @param p - the current point
     * @return new point at the new position, will make the ball "move"
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point p1 = new Point(newX, newY);
        return p1;
    }

}