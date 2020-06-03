package geometry;

/**
 * Class Point.
 * @author Eldad Levi 316363399
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x - x value of the point
     * @param y - y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - another point
     * @return the distance between two points
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt((this.y - other.getY()) * (this.y - other.getY())
                + (this.x - other.getX()) * (this.x - other.getX()));
        return distance;
    }

    /**
     * @param other - another point
     * @return true if the points are equal, false otherwise
     */

    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * @return x value
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * @return y value
     */
    public double getY() {
        return this.y;
    }
}