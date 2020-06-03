package game;

import biuoop.GUI;

/**
 * Class SubMenuTask.
 * @author Eldad Levi 316363399
 */
public class SubMenuTask implements Task {
    private AnimationRunner runner;
    private GUI gui;
    private Menu menu;

    /**
     * @param runner - the AnimationRunner parameter
     * @param menu   - the menu parameter
     * @param gui    - the gui parameter
     */
    public SubMenuTask(AnimationRunner runner, Menu menu, GUI gui) {
        this.runner = runner;
        this.gui = gui;
        this.menu = menu;
    }

    @Override
    public Object run() {
        this.runner.run(this.menu);
        Task task = (Task) this.menu.getStatus();
        task.run();

        return null;
    }
}
