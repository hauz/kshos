package kshos.core;

import java.util.ArrayList;
import kshos.core.objects.User;

/**
 * User management class.
 *
 * @author <a href="mailto:miroslav.hauser@syntacticsugar.com">Miroslav Hauser</a>
 * @version 0.01, 1.11.2009
 */
public class UserManager {

    private ArrayList<User> allowedList = null;
    private ArrayList<User> activeList = null;

    // <editor-fold defaultstate="collapsed" desc="Singleton implementation.">

    private static UserManager instance = null;

    /**
     * Initial constructor.
     */
    protected UserManager() {
        this.allowedList = new ArrayList<User>(4);
        this.activeList = new ArrayList<User>();

        addUsers();
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
    public static UserManager instance() {

        synchronized (UserManager.class) {
            if (instance == null) {
                instance = new UserManager();
            }
        }

        return instance;
    }

    // </editor-fold>

    /**
     * Validates whether the user is allowed to acces the system or not.
     *
     * Return flags:
     *     -1       Not allowed
     *      0       Allowed
     *      1       Already logged in
     *
     * @return permission status
     */
    public int validateAndLoginUser(String userName) {
        // TODO: optimalize by overriding arraylist contains method

        for (User user : allowedList) {

            if (user.getUserName().equals(userName)) {

                // user is allowed
                for (User tmpUser : activeList) {

                    if (tmpUser.getUserName().equals(userName)) {

                        // user is already logged in
                        return 1;
                    }

                }

                loginUser(userName);
                return 0;
            }
        }

        // user is not allowed to log in
        return -1;
    }

    /**
     * Add user into active users list.
     *
     * @param userName
     */
    private void loginUser(String userName) {
        activeList.add(new User(userName));
    }

    /**
     * Add user names acceptable by system.
     * Called from constructor.
     */
    private void addUsers() {
        allowedList.add(new User("hauz"));
        allowedList.add(new User("k4chn1k"));
        allowedList.add(new User("sysek"));
        allowedList.add(new User("guest"));
    }

    /**
     * Log the user out.
     * 
     * @param userName
     */
    public void logoutUser(String userName) {

        if ((userName == null) || (userName.trim().equals(""))) {
            return;
        }

        for (User user : activeList) {

            // have found the user
            if (user.getUserName().equals(userName)) {
                activeList.remove(user);
                return;
            }
        }
    }
}
