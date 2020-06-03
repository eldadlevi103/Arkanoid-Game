package game;

import java.util.ArrayList;
import geometry.Rectangle;
import geometry.Point;
import sprites.Block;
import sprites.BlockBuild;
import sprites.SpaceBuild;

/**
 * Class BlocksFromSymbolsFactory.
 * @author Eldad Levi 316363399
 */
public class BlocksFromSymbolsFactory {
    private ArrayList<BlockBuild> blocksList;
    private ArrayList<SpaceBuild> spacesList;

    /**
     * @param blocks     - the blocks list
     * @param spacesList - the spaces list
     */
    public BlocksFromSymbolsFactory(ArrayList<BlockBuild> blocks, ArrayList<SpaceBuild> spacesList) {
        this.blocksList = blocks;
        this.spacesList = spacesList;
    }

    /**
     * @param s - the symbol we check
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        if (s.equals("-")) {
            return true;
        }
        return false;
    }

    /**
     * @param s - the symbol we check
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        for (BlockBuild block : this.blocksList) {
            if (s.isEmpty()) {
                return false;
            }
            if (s.charAt(0) == block.getSymbol()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param s    - the symbol that symbols the block
     * @param xpos - the x position of the upper left point of the block
     * @param ypos -the y position of the upper left point of the block
     * @return - a block according to the definitions associated
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockBuild data = null;
        for (BlockBuild x : this.blocksList) {
            if (x.getSymbol() == s.charAt(0)) {
                data = x;
            }
        }
        Point p = new Point(xpos, ypos);
        Rectangle rect = new Rectangle(p, data.getWidth(), data.getHeight());
        Block b = new Block(rect, data.getForce());
        b.setFill(data.getFill());
        b.setFillK(data.getFillK());
        b.setStroke(data.getStroke());
        return b;
    }

    /**
     * @param s - the symbol that symbols the space
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        for (SpaceBuild x : this.spacesList) {
            if (x.getSymbol().equals(s)) {
                return x.getWidth();
            }
        }
        return 0;
    }
}
