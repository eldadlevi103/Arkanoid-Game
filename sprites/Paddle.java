package sprites;

import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import geometry.Line;
import game.GameLevel;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Class Paddle.
 * @author Eldad Levi 316363399
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rect;
    private int width;
    private int speed1;

    /**
     * @param rect  - the shape of the paddle
     * @param color - the color of the paddle
     * @param k     - the keyboardsensor for the paddle
     * @param width - the width of the paddle
     * @param speed - the speed of the paddle
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor k, int width, int speed) {
        this.rect = rect;
        this.keyboard = k;
        this.color = color;
        this.width = width;
        this.speed1 = speed;
    }

    /**
     * "moving" the paddle left by changing its X point.
     */
    public void moveLeft() {
        double lefterX = this.rect.getUpperLeft().getX() - this.speed1;
        if (lefterX < 15) {
            lefterX = 15;
            Rectangle newRect1 = new Rectangle(new Point(lefterX, this.rect.getUpperLeft().getY()), this.width, 15);
            this.rect = newRect1;
        }
        Rectangle newRect = new Rectangle(new Point(lefterX, this.rect.getUpperLeft().getY()), this.width, 15);
        this.rect = newRect;

    }

    /**
     * "moving" the paddle right by changing its X point.
     */
    public void moveRight() {
        double righterX = this.rect.getUpperLeft().getX() + this.speed1;
        if (righterX > 785 - this.width) {
            righterX = 785 - this.width;
            Rectangle newRect1 = new Rectangle(new Point(righterX, this.rect.getUpperLeft().getY()), this.width, 15);
            this.rect = newRect1;
        }
        Rectangle newRect = new Rectangle(new Point(righterX, this.rect.getUpperLeft().getY()), this.width, 15);
        this.rect = newRect;
    }

    /**
     * the actual "moving".
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * draws the paddle.
     * @param d - the drawSurface parameter
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

    }

    /**
     * @return rect - the shape of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param hitter          - the ball that hits the paddle
     * @param collisionPoint  - the point where the ball hits the block
     * @param currentVelocity - the velocity the ball came to the block
     * @return V - the new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Point upperRightOfRect = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY());
        Point downLeftOfRect = new Point(this.rect.getUpperLeft().getX(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        Point downRightOfRect = new Point(upperRightOfRect.getX(), downLeftOfRect.getY());

        Line upperWidthOfRect = new Line(this.rect.getUpperLeft(), upperRightOfRect);
        Line downWidthOfRect = new Line(downLeftOfRect, downRightOfRect);
        Line leftHeightOfRect = new Line(this.rect.getUpperLeft(), downLeftOfRect);
        Line rightHeightOfRect = new Line(upperRightOfRect, downRightOfRect);
        // we want to make the game for fun, so divides the paddle to 5 and another
        // angle to each part
        // the 1/5 part- angel 300
        if (upperWidthOfRect.isThePointOnLine(collisionPoint) || downWidthOfRect.isThePointOnLine(collisionPoint)) {
            if (collisionPoint.getX() - this.rect.getUpperLeft().getX() <= this.width / 5
                    && collisionPoint.getX() - this.rect.getUpperLeft().getX() >= 0) {
                double angle = 300;
                Point zeroAxes = new Point(0, 0);
                Point speedValues = new Point(currentVelocity.getDx(), currentVelocity.getDy());
                double speed = zeroAxes.distance(speedValues);
                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                return v;
            }
            // the 2/5 part- angel 330
            if (collisionPoint.getX() - this.rect.getUpperLeft().getX() <= (this.width / 5) * 2
                    && collisionPoint.getX() - this.rect.getUpperLeft().getX() >= this.width / 5) {
                double angle = 330;
                Point zeroAxes = new Point(0, 0);
                Point speedValues = new Point(currentVelocity.getDx(), currentVelocity.getDy());
                double speed = zeroAxes.distance(speedValues);
                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                return v;
            }
            // the 4/5 part= angle 30
            if (collisionPoint.getX() - this.rect.getUpperLeft().getX() <= (this.width / 5) * 4
                    && collisionPoint.getX() - this.rect.getUpperLeft().getX() >= (this.width / 5) * 3) {
                double angle = 30;
                Point zeroAxes = new Point(0, 0);
                Point speedValues = new Point(currentVelocity.getDx(), currentVelocity.getDy());
                double speed = zeroAxes.distance(speedValues);
                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                return v;
            }
            // the 5/5 part- angle 60
            if (collisionPoint.getX() - this.rect.getUpperLeft().getX() <= this.width
                    && collisionPoint.getX() - this.rect.getUpperLeft().getX() >= (this.width / 5) * 4) {
                double angle = 60;
                Point zeroAxes = new Point(0, 0);
                Point speedValues = new Point(currentVelocity.getDx(), currentVelocity.getDy());
                double speed = zeroAxes.distance(speedValues);
                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                return v;
            }
            // incase we just hit the paddle from 0 angle
            Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        // incase we hit the paddle from the sides of it
        if (leftHeightOfRect.isThePointOnLine(collisionPoint) || rightHeightOfRect.isThePointOnLine(collisionPoint)) {
            Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            return v;
        }
        return null;
    }

    /**
     * Add this paddle to the game.
     * @param g - the game parameter
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove this paddle from the game.
     * @param g the game paramter
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

}