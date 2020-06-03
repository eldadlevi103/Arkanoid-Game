package game;

/**
 * Class QuitTask.
 * @author Eldad Levi 316363399
 */
public class QuitTask implements Task {
    private AnimationRunner ar;
    private MenuAnimation ma;

    /**
     * @param ar - the animationRunner parameter
     * @param ma - the menuAnimation parameter
     */
    public QuitTask(AnimationRunner ar, MenuAnimation ma) {
        this.ar = ar;
        this.ma = ma;
    }

    @Override
    public Object run() {
        this.ar.getGui().close();
        System.exit(1);
        return null;
    }

}
