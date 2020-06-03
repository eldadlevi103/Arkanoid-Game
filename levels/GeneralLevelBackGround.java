package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import sprites.Sprite;


/**
 * Class GeneralLevelBackGround.
 * @author Eldad Levi 316363399
 */
public class GeneralLevelBackGround implements Sprite {
    private String line;
    private String imageName;
    private File file;
    private Image img;
    private int height;
    private int width;

    /**
     * @param line   - the line that defines the background
     * @param height - the height of the frame of the background
     * @param width  -the width of the frame of the background
     */
    public GeneralLevelBackGround(String line, int height, int width) {
        this.line = line;
        this.height = height;
        this.width = width;

        if (this.line.charAt(0) == 'i') { // if image
            String cheack = this.line;
            cheack = cheack.substring(7);
            if (!cheack.startsWith("lock_")) { // if its background image

                this.imageName = this.line.substring(24);

                ClassLoader c = ClassLoader.getSystemClassLoader();
                InputStream is = c.getResourceAsStream(this.imageName);
                try {
                    this.img = ImageIO.read(is);
                } catch (IOException e) {
                    System.out.println();
                }

            } else { // if its block_image
                this.imageName = this.line.substring(19, this.line.length() - 1);

                ClassLoader c = ClassLoader.getSystemClassLoader();
                InputStream is = c.getResourceAsStream(this.imageName);
                try {
                    this.img = ImageIO.read(is);
                } catch (IOException e) {
                    System.out.println();
                }

            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        String s = this.line;
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 15);
        if (s.charAt(0) == 'i') {
            d.drawImage(0, 0, this.img);

        } else if (s.contains("RGB")) {
            String colorValue = this.line.substring(10, s.length() - 1);
            String[] information = colorValue.split(",");
            int firstValue = Integer.parseInt(information[0]);
            int secondValue = Integer.parseInt(information[1]);
            int thirdValue = Integer.parseInt(information[2]);

            Color color = new Color(firstValue, secondValue, thirdValue);
            d.setColor(color);
            d.fillRectangle(0, 0, this.width, this.height);
        } else {

            String color = this.line.substring(6);
            switch (color) {
            case "black":
                d.setColor(Color.black);
                break;
            case "blue":
                d.setColor(Color.blue);
                break;
            case "cyan":
                d.setColor(Color.cyan);
                break;
            case "gray":
                d.setColor(Color.gray);
                break;
            case "lightGray":
                d.setColor(Color.lightGray);
                break;
            case "green":
                d.setColor(Color.green);
                break;
            case "pink":
                d.setColor(Color.pink);
                break;
            case "orange":
                d.setColor(Color.orange);
                break;
            case "red":
                d.setColor(Color.red);
                break;
            case "white":
                d.setColor(Color.white);
                break;
            case "yellow":
                d.setColor(Color.yellow);
                break;
            default:
                break;
            }
            d.fillRectangle(0, 0, this.width, this.height);
        }
    }

    /**
     * @param d    - the drawSurface parameter
     * @param xpos - the X position of the background
     * @param ypos - the Y position of the background
     */
    public void drawOn(DrawSurface d, int xpos, int ypos) {
        String s = this.line;

        if (s.charAt(0) == 'i') {
            d.drawImage(xpos, ypos, this.img);

        } else if (s.contains("RGB")) {
            String colorValue = this.line.substring(10, s.length() - 2);
            String[] information = colorValue.split(",");
            int firstValue = Integer.parseInt(information[0]);
            int secondValue = Integer.parseInt(information[1]);
            int thirdValue = Integer.parseInt(information[2]);

            Color color = new Color(firstValue, secondValue, thirdValue);
            d.setColor(color);
            d.fillRectangle(xpos, ypos, this.width, this.height);
        } else {

            String color = this.line.substring(6, s.length() - 1);
            switch (color) {
            case "black":
                d.setColor(Color.black);
                break;
            case "blue":
                d.setColor(Color.blue);
                break;
            case "cyan":
                d.setColor(Color.cyan);
                break;
            case "gray":
                d.setColor(Color.gray);
                break;
            case "lightGray":
                d.setColor(Color.lightGray);
                break;
            case "green":
                d.setColor(Color.green);
                break;
            case "pink":
                d.setColor(Color.pink);
                break;
            case "orange":
                d.setColor(Color.orange);
                break;
            case "red":
                d.setColor(Color.red);
                break;
            case "white":
                d.setColor(Color.white);
                break;
            case "yellow":
                d.setColor(Color.yellow);
                break;
            default:
                break;
            }
            d.fillRectangle(xpos, ypos, this.width, this.height);
        }
    }

    @Override
    public void timePassed() {

    }

}
