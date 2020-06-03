package geometry;
import java.util.List;
import java.util.ArrayList;

/**
 * Class Rectangle.
 * @author Eldad Levi 316363399
 */
public class Rectangle {
    private Point upperLeftPoint;
    private double width;
    private double height;
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * @param line - the line we check intersections points
     * @return intersection points list -  a (possibly empty) List of intersection points
     */
    public java.util.List intersectionPoints(Line line) {

        List intersectionPointsList = new ArrayList<Point>();

        Point upperRightOfRect = new Point(this.upperLeftPoint.getX() + this.getWidth(), this.upperLeftPoint.getY());
        Point downLeftOfRect = new Point(this.upperLeftPoint.getX(), this.upperLeftPoint.getY() + this.height);
        Point downRightOfRect = new Point(upperRightOfRect.getX(), downLeftOfRect.getY());

        Line upperWidthOfRect = new Line(this.upperLeftPoint, upperRightOfRect);
        Line downWidthOfRect = new Line(downLeftOfRect, downRightOfRect);
        Line leftHeightOfRect = new Line(this.upperLeftPoint, downLeftOfRect);
        Line rightHeightOfRect = new Line(upperRightOfRect, downRightOfRect);

        if (line.isIntersecting(upperWidthOfRect)) {
            intersectionPointsList.add(line.intersectionWith(upperWidthOfRect));
        }
        if (line.isIntersecting(downWidthOfRect)) {
            intersectionPointsList.add(line.intersectionWith(downWidthOfRect));
        }
        if (line.isIntersecting(leftHeightOfRect)) {
            intersectionPointsList.add(line.intersectionWith(leftHeightOfRect));
        }
        if (line.isIntersecting(rightHeightOfRect)) {
            intersectionPointsList.add(line.intersectionWith(rightHeightOfRect));
        }
        return intersectionPointsList;
    }
/**
 * @return the width and width of the rectangle
 */
    public double getWidth() {
        return this.width;
    }
/**
 * @return the width and height of the rectangle
 */
    public double getHeight() {
        return this.height;
    }
/**
 * @return the upper-left point of the rectangle.
 */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }
}