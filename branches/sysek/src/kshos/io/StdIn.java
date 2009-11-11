package kshos.io;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
    public void stdOpenIn();

    /**
     * Read char.
     * @return Char or null if there is nothing to read.
     */
    public char stdRead();
    
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
