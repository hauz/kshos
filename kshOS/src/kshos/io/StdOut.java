package kshos.io;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
    public void stdOpenOut();

    /**
     * Write char.
     */
    public void stdWrite(char c);

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

