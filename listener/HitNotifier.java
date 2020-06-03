package listener;

/**
 * Interface HitNotifier.
 * @author Eldad Levi 316363399
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the hitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the hitListener
     */
    void removeHitListener(HitListener hl);
}