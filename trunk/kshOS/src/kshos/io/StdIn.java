package kshos.io;

/**
 * Standart input interface
 * Contains necessary methods for input stream.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.02, 11.11.2009
 */
public interface StdIn {

    /**
     * Open input stream.
     */
    public boolean stdOpenIn();
    
    /**
     * Read line.
     * @return Line or null if there is nothing to read.
     */
    public String stdReadln();

     /**
     * Close input stream.
     */
    public void stdCloseIn();
}
