/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.io;

/**
 * Standart input interface
 * Contains necessary methods for ouput stream.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 5.11.2009
 */
public interface StdOut {

    /**
     * Open ouput stream.
     */
    public void open();

    /**
     * Write char.
     */
    public void write(char c);

    /**
     * Write line.
     */
    public void writeln(String s);

     /**
     * Close output stream.
     */
    public void close();
}

