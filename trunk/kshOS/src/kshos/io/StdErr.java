package kshos.io;

/**
 * Standart error interface
 * Contains necessary methods for error stream.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 23.11.2009
 */
public interface StdErr {

    /**
     * Open ouput stream.
     */
    public boolean stdOpenOut();

    /**
     * Write line.
     */
    public void stdWriteln(String s);

    /**
     * Append string.
     */
    public void stdAppend(String s);

     /**
     * Close output stream.
     */
    public void stdCloseOut();
}

