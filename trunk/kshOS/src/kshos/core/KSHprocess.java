/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import kshos.io.*;

/**
 * Simple process.
 * Replace by final version of process.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 11/11/2009
 */
public abstract class KSHprocess extends Thread {

    protected long PID = 0;
    private StdIn in;
    private StdOut out;
    private File workingDir;
    private String[] args;
    private KSHprocess parent;
    private KSHprocess child;


    /**
     * Get arguments.
     * @return array of arguments
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Set arguments.
     * @param arg arraylist from parser
     */
    public void setArgs(ArrayList<String> arg) {
        args = new String[arg.size() - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = arg.get(i);
        }
    }

    /**
     * Get child for actual process.
     *
     * @return child
     */
    public KSHprocess getChild() {
        return child;
    }

    /**
     * Set child for actual process.
     *
     * @param child
     */
    public void setChild(KSHprocess child) {
        this.child = child;
    }

    /**
     * Return the parent of actual process.
     *
     * @return parent
     */
    public KSHprocess getParent() {
        return parent;
    }

    /**
     * Set parent of actual process.
     *
     * @param parent
     */
    public void setParent(KSHprocess parent) {
        this.parent = parent;
    }

    /**
     * Get process input.
     * @return input
     */
    public StdIn getIn() {
        return in;
    }

    /**
     * Set process input.
     * @param in - new input
     */
    public void setIn(StdIn in) {
        this.in = in;
    }

    /**
     * Get process output.
     * @return output
     */
    public StdOut getOut() {
        return out;
    }

    /**
     * Set process output.
     * @param out - new output
     */
    public void setOut(StdOut out) {
        this.out = out;
    }

    /**
     * Process working directory.
     * @return path of working directory
     */
    public String getWorkingDir() {
        try {
            return this.workingDir.getCanonicalPath();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error!";
        }
    }

    /**
     * Set new working directory.
     * @param dest path for working directory
     */
    public void setWorkingDir(File dest) {
        this.workingDir = dest;
    }

    /**
     * Return process unique ID
     *
     * @return PID
     */
    public long getPID() {
        return PID;
    }

    /**
     * Set process unique ID
     *
     * @param PID
     */
    public void setPID(long pid) {
        this.PID = pid;
    }

    /**
     * Abstract method for line processing.
     * @param line line for processing
     */
    public abstract void processLine(String line);

    /**
     * Abstract method for signal processing.
     * @param type type of signal
     */
    public abstract void processSignal(int type);

}
