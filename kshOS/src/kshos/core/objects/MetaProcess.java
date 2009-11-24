package kshos.core.objects;

/**
 * Class holds information about one single process.
 * It is used mainly to provide process information in secured fashion.
 *
 * @author Miroslav Hauser
 * @author <a href="mailto:hauz@students.zcu.cz">Miroslav Hauser</a>
 */
public class MetaProcess {

    private String name;
    private long PID;
    private String user;

    /**
     * Create new metaprocess with defined parameters.
     *
     * @param user
     * @param process name or command name
     * @param PID
     */
    public MetaProcess(String user, String name, long PID) {
        this.name = name;
        this.PID = PID;
        this.user = user;
    }

    /**
     * Process PID.
     *
     * @return PID
     */
    public long getPID() {
        return PID;
    }

    /**
     * Provess name or command name.
     * 
     * @return process name or command name.
     */
    public String getName() {
        return name;
    }

    /**
     * User name.
     *
     * @return user name.
     */
    public String getUser() {
        return user;
    }

    /**
     * String representation of object.
     *
     * @return object representation
     */
    @Override
    public String toString() {
        return this.PID + "\t" + this.user + "\t" + this.name;
    }
}
