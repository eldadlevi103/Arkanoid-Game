package geometry;
import java.util.ArrayList;

/**
 * Class Line.
 * @author Eldad Levi 316363399
 */
public class Line {
    private static final Point NULL = null;
    private Point start;
    private Point end;

    /**
     * @param start -the starting point of the line
     * @param end   - the ending point of the line
     */
    // first constructor
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 - the x of the start point
     * @param y1 - the y of the start point
     * @param x2 - the x of the end point
     * @param y2 - the y of the end point
     */
    // second constructor
    public Line(double x1, double y1, double x2, double y2) {
        Point start1 = new Point(x1, y1);
        Point end1 = new Point(x2, y2);
        this.start = start1;
        this.end = end1;
    }

    /**
     * using the distance function in order to calculate the length of the line.
     * @return length of the line
     */
    public double length() {
        double length = this.start.distance(this.end);
        return length;
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        Point middleP = new Point(middleX, middleY);
        return middleP;
    }

    /**
     * @return start point of the line
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * @return end point of the line
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * doing mathematical calculations in order to know if there is an intersection
     * point between two lines.
     * @param other - the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double ax = this.end.getX() - this.start.getX();
        double ay = this.end.getY() - this.start.getY();
        double bx = other.end.getX() - other.start.getX();
        double by = other.end.getY() - other.start.getY();

        double summeraize = (ax * by) - (ay * bx); // if the lines are parallel
        if (summeraize == 0) {
            return false;
        }
        double cx = other.start.getX() - this.start.getX();
        double cy = other.start.getY() - this.start.getY();
        double t = (cx * by - cy * bx) / summeraize;
        if (t < 0 || t > 1) {
            return false;
        }
        double u = (cx * ay - cy * ax) / summeraize;
        if (u < 0 || u > 1) {
            return false;
        }
        return true;
    }

    /**
     * using mathematical formulas do get the intersection point.
     * @param other - the other Line
     * @return the intersection point if the lines intersect, and null otherwise
     */

    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        } else {
            double a1 = this.end.getY() - this.start.getY();
            double b1 = this.start.getX() - this.end.getX();
            double c1 = a1 * this.start.getX() + b1 * this.start.getY();

            double a2 = other.end.getY() - other.start.getY();
            double b2 = other.start.getX() - other.end.getX();
            double c2 = a2 * other.start.getX() + b2 * other.start.getY();

            double differnce = (a1 * b2) - (a2 * b1);

            double intersectionX = (b2 * c1 - b1 * c2) / differnce;
            double intersectionY = (a1 * c2 - a2 * c1) / differnce;
            Point intersectionPoint = new Point(intersectionX, intersectionY);
            return intersectionPoint;
        }
    }

    /**
     * checking if the lines are the same.
     * @param other - the other Line.
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null. Otherwise,
     * return the closest intersection point to the start of the line.
     * @param rect - the intersection is between the rectangle vertices and this         line
     * @return intersectionPoint - the point of the intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> points = (ArrayList<Point>) rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        int theClosetIntersectionPoint = 0;
        double min = this.getStart().distance((Point) points.get(0));
        for (int i = 0; i < points.size(); i++) {
            double temp = this.getStart().distance((Point) points.get(i));
            if (temp < min) {
                min = temp;
                theClosetIntersectionPoint = i;
            }
        }
        return (Point) points.get(theClosetIntersectionPoint);
    }

    /**
     * simple method to check if the point is on the line.
     * @param point - the checked point
     * @return true or false -depends if the point is on the line or not
     */
    public boolean isThePointOnLine(Point point) {
        Point a = new Point(this.getStart().getX(), this.getStart().getY());
        Point b = new Point(this.getEnd().getX(), this.getEnd().getY());
        if (a.distance(point) + b.distance(point) == a.distance(b)) {
            return true;
        }
        return false;
    }
}
