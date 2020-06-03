package sprites;

/**
 * Class SpaceBuild.
 * @author Eldad Levi 316363399
 */
public class SpaceBuild {
    private String symbol;
    private int width;

    /**
     * @return the symbol of the space
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * @param string1 - setting the symbol of the space
     */
    public void setSymbol(String string1) {
        this.symbol = string1;
    }

    /**
     * @return the width of the space
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @param width1 - setting the wdith of the space
     */
    public void setWidthFromString(String width1) {
        this.width = Integer.parseInt(width1);
    }

}
