package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import levels.GeneralLevelBackGround;
import geometry.Line;
import listener.HitListener;
import listener.HitNotifier;
import game.GameLevel;

/**
 * Class Block.
 * @author Eldad levi 316363399
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private double force;
    private ArrayList<HitListener> hitListeners;
    private Sprite fill;
    private Map<Integer, Sprite> fillK;
    private java.awt.Color stroke;

    /**
     * @param rect  - the shape of the block
     * @param force - the number of hits that the block can be hited
     */
    public Block(Rectangle rect, double force) {
        this.rect = rect;
        this.force = force;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @param hitter          - the ball that hits the block
     * @param collisionPoint  - the point where the ball hits the block
     * @param currentVelocity - the velocity the ball came to the block
     * @return V - the new velocity after the hit
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // decrease the number of hits the block can take
        if (this.force > 0) {
            this.force--;
        }

        Point upperRightOfRect = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY());
        Point downLeftOfRect = new Point(this.rect.getUpperLeft().getX(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        Point downRightOfRect = new Point(upperRightOfRect.getX(), downLeftOfRect.getY());
        // making the vertices of the rectangle
        Line upperWidthOfRect = new Line(this.rect.getUpperLeft(), upperRightOfRect);
        Line downWidthOfRect = new Line(downLeftOfRect, downRightOfRect);
        Line leftHeightOfRect = new Line(this.rect.getUpperLeft(), downLeftOfRect);
        Line rightHeightOfRect = new Line(upperRightOfRect, downRightOfRect);

        // if the ball comes to the upper left corner
        if (upperWidthOfRect.isThePointOnLine(collisionPoint) && leftHeightOfRect.isThePointOnLine(collisionPoint)) {
            if (currentVelocity.getDx() > 0) {
                Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
            if (currentVelocity.getDy() > 0) {
                Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
        }
        // if the ball comes to the down left corner
        if (downWidthOfRect.isThePointOnLine(collisionPoint) && leftHeightOfRect.isThePointOnLine(collisionPoint)) {
            if (currentVelocity.getDx() > 0) {
                Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
            if (currentVelocity.getDy() < 0) {
                Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
        }
        // if the ball comes to the upper right corner
        if (upperWidthOfRect.isThePointOnLine(collisionPoint) && rightHeightOfRect.isThePointOnLine(collisionPoint)) {
            if (currentVelocity.getDx() < 0) {
                Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!

                return v;
            }
            if (currentVelocity.getDy() > 0) {
                Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!

                return v;
            }
        }
        // if the ball comes to the down right corner
        if (downWidthOfRect.isThePointOnLine(collisionPoint) && rightHeightOfRect.isThePointOnLine(collisionPoint)) {
            if (currentVelocity.getDx() < 0) {
                Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
            if (currentVelocity.getDy() < 0) {
                Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                this.notifyHit(hitter); // Notify that we have hit!
                return v;
            }
        }

        // if we have a collision in the two vertices verticals
        if (upperWidthOfRect.isThePointOnLine(collisionPoint) || downWidthOfRect.isThePointOnLine(collisionPoint)) {
            Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            this.notifyHit(hitter); // Notify that we have hit!
            return v;
        }
        // if we have a collision in the two horizontal vertices
        if (leftHeightOfRect.isThePointOnLine(collisionPoint) || rightHeightOfRect.isThePointOnLine(collisionPoint)) {
            Velocity v = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            this.notifyHit(hitter); // Notify that we have hit!
            return v;
        }

        return null;

    }

    /**
     * @return - the shape - rectangle of the block
     */
    public Rectangle getCollisionRectangle() {

        return this.rect;
    }

    /**
     * @return the force of the block.
     */
    public double getForce() {
        return this.force;

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * will draw the blocks.
     * @param d - the drawSurface parameter
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        if (this.fillK != null) { // k_fill
            int key = (int) this.force;
            if (this.fillK.containsKey(key)) {
                GeneralLevelBackGround glbg = (GeneralLevelBackGround) this.fillK.get((int) key);
                glbg.drawOn(d, x, y);
            } else {
                GeneralLevelBackGround glbg = (GeneralLevelBackGround) fill;
                glbg.drawOn(d, x, y);
            }

        } else if (this.fill != null) { // regular fill
            GeneralLevelBackGround glbg = (GeneralLevelBackGround) fill;
            glbg.drawOn(d, x, y);

        } else { // pre 7
            d.setColor(Color.GRAY);
            d.fillRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
            d.drawRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
        if (this.stroke != null) { // stroke
            d.setColor(this.stroke);
            d.drawRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
    }

    /**
     * @param game1 - initialize the actual game
     */
    public void addToGame(GameLevel game1) {
        game1.addCollidable(this);
        game1.addSprite(this);
    }

    /**
     * @param game1 - initialize the actual game
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeSprite(this);
        game1.removeCollidable(this);
    }

    /**
     * @param hl - the hitListener parameter
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl - the hitListener parameter
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all the listeners that we have a hit.
     * @param hitter - the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @param fill1 - the fill color
     */
    public void setFill(Sprite fill1) {

        this.fill = fill1;
    }

    /**
     * @param fill1 - the fill color
     */
    public void setFillK(Map<Integer, Sprite> fill1) {

        this.fillK = fill1;
    }

    /**
     * @param stroke1 - the stroke color
     */
    public void setStroke(java.awt.Color stroke1) {

        this.stroke = stroke1;
    }

}
