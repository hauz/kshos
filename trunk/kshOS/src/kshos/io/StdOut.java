package kshos.io;

/**
 * Standart input interface
 * Contains necessary methods for ouput stream.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.02, 11.11.2009
 */
public interface StdOut {

    /**
     * Open ouput stream.
     */
    public boolean stdOpenOut();

    /**
     * Write line.
     */
    public void stdWriteln(String s);

    /**
     * Write line.
     */
    public void stdAppend(String s);

     /**
     * Close output stream.
     */
    public void stdCloseOut();
}

