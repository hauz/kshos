package kshos.ui;

import java.util.ArrayList;

/**
 * Class manages all user interfaces (consoles).
 * Design Pattern: Singleton
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class UIManager {

    private static UIManager instance = null;
    private ArrayList<UserInterface> interfaces = null;

    /**
     * Main constructor
     */
    protected UIManager() {
        interfaces = new ArrayList<UserInterface>();
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
    public static UIManager instance() {
        synchronized (UIManager.class) {
            if (instance == null) {
                instance = new UIManager();
            }
        }

        return instance;
    }

    /**
     * Creates new console.
     */
    public void newConsole(String userName) {
        UserInterface newUI = new UserInterface(userName);
        interfaces.add(newUI);
        newUI.setVisible(true);
    }

    /**
     * Closes all consoles.
     */
    public void closeAllConsoles() {
        for (UserInterface ui: interfaces) {
            closeConsole(ui);
        }
    }

    /**
     * Closes user interface.
     *
     * @param console
     */
    public void closeConsole(UserInterface console) {
        console.close();
    }
}
