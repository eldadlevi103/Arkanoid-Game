package game;

/**
 * Interface Task.
 * @author Eldad Levi 316363399
 * @param <T> - the Task parameter
 */
public interface Task<T> {
    /**
     * @return running the task
     */
    T run();
}
