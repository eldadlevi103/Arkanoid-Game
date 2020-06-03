package sprites;

/**
 * Interface BlockCreator.
 * @author Eldad Levi 316363399
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos - the X position of the block
     * @param ypos - the Y position of the block
     * @return the new Block
     */

    Block create(int xpos, int ypos);
}
