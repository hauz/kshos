/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.io;

import java.io.File;

/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 5.11.2009
 */
public class StdFile implements StdIn, StdOut {
    private File file;

    public StdFile(File file) {
        this.file = file;
    }

    public void open() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public char read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String readln() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void write(char c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void writeln(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
