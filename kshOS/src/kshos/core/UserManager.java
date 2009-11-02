package kshos.core;

/**
 * User management class.
 *
 * @author <a href="mailto:miroslav.hauser@syntacticsugar.com">Miroslav Hauser</a>
 * @version 0.01, 1.11.2009
 */
public class UserManager {

    private static UserManager instance = null;

    /**
     * Initial constructor.
     */
    protected UserManager() {
        // blank constructor
    }

    /**
     * Destroy actual instance.
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Actual class instance.
     *
     * @return instance
     */
    public UserManager instance() {

        synchronized(UserManager.class) {
            if (instance == null) {
                instance = new UserManager();
            }
        }

        return instance;
    }



}
