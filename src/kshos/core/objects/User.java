package kshos.core.objects;

/**
 * Class represents one single user.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 5.11.2009
 */
public class User implements Comparable<User> {

    private String userName = null;     // user name
    private int UID = 0;                // user id

    /**
     * Initial constructor.
     *
     * @param userName
     */
    public User(String userName) {
        this.userName = userName;
    }

    /**
     * Returns user id.
     *
     * @return UID
     */
    public int getUID() {
        return UID;
    }

    /**
     * Set user id.
     *
     * @param UID
     */
    public void setUID(int UID) {
        this.UID = UID;
    }

    /**
     * Return user name.
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set user name.
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Class comparator.
     * 
     * @param object to compare
     * @return 0 or 1 as the object is equal to or not equal than the specific
     * object.
     */
    public int compareTo(User o) {

        if (o.getUserName().equals(this.userName)) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
