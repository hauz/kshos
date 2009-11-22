/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.core.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import kshos.io.*;

/**
 * Simple process.
 * Replace by final version of process.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.03 11/11/2009
 *
 * Changelog:
 *      0.03 - Miroslav Hauser
 *          *
 */
public abstract class Process extends Thread {

    private long PID = 0;
    private Process parent;
    private TreeMap<Long, Process> childs = new TreeMap<Long, Process>();

    private StdIn in;
    private StdOut out;
    private File workingDir;
    private String[] args;


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
    public Process getChild(long PID) {
        return this.childs.get(PID);
    }

    /**
     * Returns TheeMap of process childs.
     *
     * @return childs
     */
    public TreeMap<Long, Process> getAllChilds() {
        return this.childs;
    }

    /**
     * Removes process from child list.
     * 
     * @param PID
     */
    public void removeChild(long PID) {
        this.childs.remove(PID);
    }

    /**
     * Set child for actual process.
     *
     * @param child
     */
    public void addChild(Process child) {
        this.childs.put(child.getPID(), child);
    }

    /**
     * Return the parent of actual process.
     *
     * @return parent
     */
    public Process getParent() {
        return parent;
    }

    /**
     * Set parent of actual process.
     *
     * @param parent
     */
    public void setParent(Process parent) {
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

    /**
     *   This method is equvalent of Java thread's void run() method.
     * this method is executed periodically. It should contain thread's
     * main logic.
     *   Class contains another two methods called preTick() and postTick().
     * Those methods could be uset to allocate and release all resources needed
     * for main logic.
     * 
     * !!! Do not forget to release all allocated resources !!!
     */
    public abstract void tick();

    /**
     * Method used to allocate all resources necessary for main logic.
     * 
     * !!! Do not forget to release all allocated resources !!!
     *
     * @return success or not [true / false]
     */
    public boolean preTick() {
        return true;
    }

    /**
     * Method used to realease all allocated resources.
     *
     * !!! Do not forget to test before releasing !!!
     */
    public void postTick() { }

    /**
     * Run method.
     */
    @Override
    public void run() {
        
        // if resource allocation was successfull then run main logic
        if (preTick()) {
            tick();
        }

        // release all allocated resources
        postTick();
    }
}


