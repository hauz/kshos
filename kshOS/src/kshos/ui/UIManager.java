package kshos.ui;

/**
 * Class manages all user interfaces (consoles).
 * Design Pattern: Singleton
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class UIManager {

    private static UIManager instance = null;

    /**
     * Main constructor
     */
    protected UIManager() {
        // not necessary
    }

    /**
     * Destroys actual instance of UIManager.
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Get actual instance of UIManager.
     *
     * @return Actual instance fo UIManager.
     */
    public static UIManager getInstance() {
        synchronized (UIManager.class) {
            if (instance == null) {
                instance = new UIManager();
            }
        }

        return instance;
    }

    public void newConsole() {
        UserInterface ui = new UserInterface("Console window");
        ui.setVisible(true);
    }


}
