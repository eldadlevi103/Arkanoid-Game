package sprites;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import levels.GeneralLevelBackGround;

/**
 * Class BlockBuild.
 * @author Eldad Levi 316363399
 */
public class BlockBuild {

    private char symbol;
    private int height;
    private int width;
    private int force;
    private Sprite fill;
    private Map<Integer, Sprite> fillK = new HashMap<Integer, Sprite>();
    private java.awt.Color color;

    /**
     * @return symbol of the block
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * @param symbol1 - setting the symbol of the block
     */
    public void setStringSymbol(String symbol1) {
        this.symbol = symbol1.charAt(0);
    }

    /**
     * @return the height of the block
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @param height1 -setting the height of the block
     */
    public void setStringHeight(String height1) {
        this.height = Integer.parseInt(height1);
    }

    /**
     * @return the widht of the block
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @param width1 - setting the width of the block
     */
    public void setStringWidth(String width1) {
        this.width = Integer.parseInt(width1);
    }

    /**
     * @return the force of the block
     */
    public int getForce() {
        return this.force;
    }

    /**
     * @param force1 - setting the force of the block
     */
    public void setStringForce(String force1) {
        this.force = Integer.parseInt(force1);
    }

    /**
     * @return fill of the block
     */
    public Sprite getFill() {
        return this.fill;
    }

    /**
     * @param fill1 - setting the fill of the block
     */
    public void setStringFill(String fill1) {
        this.fill = new GeneralLevelBackGround(fill1, this.height, this.width);
    }

    /**
     * @return a map the holds few fills
     */
    public Map<Integer, Sprite> getFillK() {
        return this.fillK;
    }

    /**
     * @param fillK1 - setting a map that holds few fills
     */
    public void setStringfillK(String fillK1) {
        this.fillK.put(fillK1.charAt(5) - '0',
                new GeneralLevelBackGround(fillK1.substring(7), this.height, this.width));
    }

    /**
     * @return the color of the stroke of the block
     */
    public java.awt.Color getStroke() {
        return this.color;
    }

    /**
     * @param stroke - setting the stroke of the block
     */
    public void setStringStroke(String stroke) {

        if (stroke.substring(0, 5).equals("color")) { // if color
            if (stroke.substring(6, 8).equals("RGB")) { // if RGB
                int firstValue = Integer.parseInt(stroke.substring(10, 10));
                int secondValue = Integer.parseInt(stroke.substring(12, 12));
                int thirdValue = Integer.parseInt(stroke.substring(14, 14));
                this.color = new Color(firstValue, secondValue, thirdValue);
            } else { // if by name
                String line = stroke.substring(6, stroke.length() - 1);
                switch (stroke.substring(6, stroke.length() - 1)) {
                case "black":
                    this.color = Color.black;
                    break;
                case "blue":
                    this.color = Color.blue;
                    break;
                case "cyan":
                    this.color = Color.cyan;
                    break;
                case "gray":
                    this.color = Color.gray;
                    break;
                case "lightGray":
                    this.color = Color.lightGray;
                    break;
                case "green":
                    this.color = Color.green;
                    break;
                case "pink":
                    this.color = Color.pink;
                    break;
                case "orange":
                    this.color = Color.orange;
                    break;
                case "red":
                    this.color = Color.red;
                    break;
                case "white":
                    this.color = Color.white;
                    break;
                case "yellow":
                    this.color = Color.yellow;
                    break;
                default:
                    break;
                }
            }
        }
    }
}