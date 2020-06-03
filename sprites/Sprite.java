package sprites;
import biuoop.DrawSurface;


/**
 * Interface Sprite.
 * @author Eldad Levi 316363399
 *
 */
public interface Sprite {
   /**
    * draw the sprite to the screen.
    * @param d - the DrawSurface parameter
    */
   void drawOn(DrawSurface d);
   /**
    * notify the sprite that time has passed.
    */
   void timePassed();
}