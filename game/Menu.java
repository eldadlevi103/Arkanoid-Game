package game;

/**
 * Interface Menu.
 * @author Eldad Levi 316363399
 * @param <T> - the task parameter
 */
public interface Menu<T> extends Animation {
    /**
     * @param key       - the key that starts the task
     * @param message   - the title of the task
     * @param returnVal - the value that opens the file that defines level
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status
     */
    T getStatus();

    /**
     * @param key     - the key that starts the task
     * @param message - the title of the task
     * @param subMenu - the subMenu screen
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
