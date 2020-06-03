package game;

/**
 * Class Selection.
 * @author Eldad Levi 316363399
 * @param <T> - the task parameter
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * @param key       - the key symbols the selection
     * @param message   - the tile of the selection
     * @param returnVal - the actual value of the selection
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @return the key of the selection
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key1 - the key symbols the selection
     */
    public void setKey(String key1) {
        this.key = key1;
    }

    /**
     * @return the title of the selection
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message1 - the title of the selection
     */
    public void setMessage(String message1) {
        this.message = message1;
    }

    /**
     * @return the actual value of the selection
     */
    public T getReturnVal() {
        return returnVal;
    }

    /**
     * @param returnVal1 -the actual value of the selection
     */
    public void setReturnVal(T returnVal1) {
        this.returnVal = returnVal1;
    }

}
