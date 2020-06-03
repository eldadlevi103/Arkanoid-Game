package listener;

/**
 * Class Counter.
 * @author Eldad Levi 316363399
 */
public class Counter {
    private int count;

    /**
     * Initialize the count to be 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     * @param number - the number we add
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     * @param number - the number we subtract
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * @return the value of the counter
     */
    public int getValue() {
        return this.count;
    }

    /**
     * @param number1 - the counter number
     */
    public void setValue(int number1) {
         this.count = number1;
    }
}