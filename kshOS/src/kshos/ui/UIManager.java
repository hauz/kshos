package kshos.ui;

import java.util.ArrayList;
import kshos.core.objects.User;

/**
 * Class manages all user interfaces (consoles).
 * Design Pattern: Singleton
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.02, 25.11.2009
 */
public class UIManager {

    private ArrayList<UserInterface> interfaces = null;

    // <editor-fold defaultstate="collapsed" desc="Singleton implementation.">

    private static UIManager instance = null;

    /**
     * Main constructor
     */
    protected UIManager() {
        initialize();
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

    // </editor-fold>

    /**
     * Creates new console.
     */
    public UserInterface newConsole(User user) {
        UserInterface newUI = new UserInterface(user);
        interfaces.add(newUI);
        newUI.setVisible(true);

        return newUI;
    }

    /**
     * Closes all consoles.
     */
    public void closeAllConsoles() {

        for (UserInterface ui: interfaces) {
            ui.close();
        }

        this.interfaces.removeAll(interfaces);
    }

    /**
     * Removes the console from the interfaces list.
     *
     * @param console
     */
    public void removeConsole(UserInterface console) {
        this.interfaces.remove(console);
    }

    /**
     * Closes user interface.
     *
     * @param console
     */
    public void closeConsole(UserInterface console) {
        interfaces.remove(console);
        console.close();
    }

    /**
     * Initializes all necessary objects.
     * Called from constructor.
     */
    private void initialize() {
        interfaces = new ArrayList<UserInterface>();
    }
}
