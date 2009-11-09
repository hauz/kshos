/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.io;

/**
 * Standart input interface
 * Contains necessary methods for input stream.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 5.11.2009
 */
public interface StdIn {

    /**
     * Open input stream.
     */
    public void open();

    /**
     * Read char.
     * @return Char or null if there is nothing to read.
     */
    public char read();
    
    /**
     * Read line.
     * @return Line or null if there is nothing to read.
     */
    public String readln();

     /**
     * Close input stream.
     */
    public void close();
}
