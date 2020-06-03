package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import biuoop.DrawSurface;

/**
 * Class SpriteCollection.
 * @author Eldad Levi 316363399
 */
public class SpriteCollection {
    private java.awt.Color[] colors;
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * making an array of colors, will be used to draw each line of blocks in
     * different color.
     */
    public SpriteCollection() {
        this.colors = new java.awt.Color[7];
        for (int i = 0; i < 7; i++) {
            Random rand = new Random();
            colors[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
    }

    /**
     * @param s - the sprite we add to the collection
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * @param s - the sprite we remove from the collection
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d - the draw surface parameter
     */
    public void drawAllOn(DrawSurface d) {

        // using a loop to draw each sprite in the specific color
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);

        }
    }

    /**
     * @return the sprite array
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

}