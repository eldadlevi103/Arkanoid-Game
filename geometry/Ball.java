package geometry;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
import sprites.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;


/**
 * Class Ball.
 * @author Eldad Levi 316363399
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point startOfFrame;
    private Point endOfFrame;
    private GameEnvironment game;

    /**
     * @param center - the center point
     * @param r      - the size of the ball
     * @param color  - the color of the ball
     */
    // first constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * @param x     -the x value of the center point
     * @param y     - the y value of the center point
     * @param r     - the size of the ball
     * @param color - the color of the ball
     */
    // second constructor
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point center1 = new Point(x, y);
        this.center = center1;
        this.r = r;
        this.color = color;
    }

    /**
     * @return the x point of the center of the ball
     */
    // accessors
    public int getX() {
        int x = (int) this.center.getX();
        return x;
    }

    /**
     * @return the y point of the center of the ball
     */
    public int getY() {
        int y = (int) this.center.getY();
        return y;
    }

    /**
     * @return the size of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface - the GUI surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLUE);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }
    /**
     * determines the velocity of the ball.
     * @param v1 - velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }
    /**
     *determines the velocity of the ball.
     * @param dx - the velocity in the x axis
     * @param dy - the velocity of the ball in the y axis
     */
    public void setVelocity(double dx, double dy) {
        Velocity v1 = new Velocity(dx, dy);
        this.v = v1;
    }
    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * the method will "move" the ball in the correct direction.
     */
    public void moveOneStep() {
        // in case the ball will not get any speed
        if (this.v == null) {
            return;
        }
        // getting the route of the ball- checking its line of movement before it
        // actually move
        double finalXPoint = this.center.getX();
        double finalYPoint = this.center.getY();
        while (finalXPoint < this.endOfFrame.getX() && finalXPoint > this.startOfFrame.getX()
                && finalYPoint > this.startOfFrame.getY() && finalYPoint < this.endOfFrame.getY()) {
            finalXPoint += this.v.getDx();
            finalYPoint += this.v.getDy();
        }
        finalXPoint -= this.v.getDx();
        finalYPoint -= this.v.getDy();
        // putting this line in trajectory line
        Line trajectory;
        trajectory = new Line(this.center.getX(), this.center.getY(), finalXPoint, finalYPoint);
        // informing the trajectory about the optional collisions
        CollisionInfo collide = this.game.getClosestCollision(trajectory);

        // making new center
        Point newCenter = this.v.applyToPoint(this.center);
        // if there are no blocks and no collisions
        if (collide == null) {

            // checking the ball is in the range (width aspect)
            if ((newCenter.getX() > this.endOfFrame.getX() - this.r)
                    || (newCenter.getX() - this.r < startOfFrame.getX())) {
                // setting the velocity in the right way
                this.setVelocity(-this.v.getDx(), this.v.getDy());
            }
            // checking the ball is in the range (height aspect)
            if ((newCenter.getY() > this.endOfFrame.getY() - this.r)
                    || (newCenter.getY() - this.r < startOfFrame.getY())) {
                // setting the velocity in the right way
                this.setVelocity(this.v.getDx(), -this.v.getDy());
            }

            this.center = this.getVelocity().applyToPoint(this.center);

        } else { // collide !=null and we have colliders in the trajectory way
            int flag = 1;
            if (this.v.getDx() == 0 && this.v.getDy() > 0 && flag == 1) { // coming from up to the block
                if (newCenter.getY() + this.r >= collide.getCollisionPoint().getY()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() == 0 && this.v.getDy() < 0 && flag == 1) { // coming from down to the block
                if (newCenter.getY() - this.r <= collide.getCollisionPoint().getY()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() > 0 && this.v.getDy() == 0 && flag == 1) { // coming from left to the block
                if (newCenter.getX() + this.r >= collide.getCollisionPoint().getX()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() < 0 && this.v.getDy() == 0 && flag == 1) { // coming from right to the block
                if (newCenter.getX() - this.r <= collide.getCollisionPoint().getX()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() > 0 && this.v.getDy() > 0 && flag == 1) { // coming from left up to the block
                if (newCenter.getY() + this.r >= collide.getCollisionPoint().getY()
                        || newCenter.getX() + this.r >= collide.getCollisionPoint().getX()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() < 0 && this.v.getDy() < 0 && flag == 1) { // coming from right down to the block
                if (newCenter.getX() - this.r <= collide.getCollisionPoint().getX()
                        || newCenter.getY() - this.r <= collide.getCollisionPoint().getY()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;

                }
            }

            if (this.v.getDx() > 0 && this.v.getDy() < 0 && flag == 1) { // coming from left down to the block
                if (newCenter.getY() - this.r <= collide.getCollisionPoint().getY()
                        || newCenter.getX() + this.r >= collide.getCollisionPoint().getX()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;
                }
            }

            if (this.v.getDx() < 0 && this.v.getDy() > 0 && flag == 1) { // coming from right up to the block
                if (newCenter.getY() + this.r >= collide.getCollisionPoint().getY()
                        || newCenter.getX() - this.r <= collide.getCollisionPoint().getX()) {
                    this.setVelocity(collide.getCollisionObject().hit(this, collide.getCollisionPoint(), this.v));
                    flag = 0;

                }
            }
            // the moving part
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * the method will initialize the frame that the ball will move in.
     * @param start - the starting point of the frame
     * @param end   - the ending point of the frame
     */
    public void setOfTheFrame(Point start, Point end) {
        this.startOfFrame = start;
        this.endOfFrame = end;
    }
    /**
     * @param game1 - initialize the actual game
     */
    public void setGameEnvironment(GameEnvironment game1) {
        this.game = game1;
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     *adding the spirte- ball into the game.
     * @param game1 - initialize the actual game
     */
    public void addToGame(GameLevel game1) {
        game1.addSprite(this);
    }

    /**
     * removing the spirte- ball into the game.
     * @param game1 - initialize the actual game
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeSprite(this);

    }
}
