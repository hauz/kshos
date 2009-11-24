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
 * @version 0.04 11/11/2009
 *
 * Changelog:
 *      <b>0.03 - Miroslav Hauser</b>
 *          * child collection updated
 *          * methods updated
 *          * thread logic updated
 *          * nameID added
 *      <b>0.04 - Miroslav Hauser</b>
 *          * stdErr added
 *          * getStdErr() and set stdErr() added
 */
public abstract class Process extends Thread {

    private long PID = 0;
    private Process parent;
    private String name = "";      // string representation; could be command, ...
    private User owner = new User("");
    private TreeMap<Long, Process> childs = new TreeMap<Long, Process>();

    private StdIn in;
    private StdOut out;
    private StdErr err;
    private File workingDir;
    private String[] args;

    // <editor-fold desc="getters" defaultstate="collapsed">

    /**
     * Get arguments.
     * @return array of arguments
     */
    public String[] getArgs() {
        return args;
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
     * Return the parent of actual process.
     *
     * @return parent
     */
    public Process getParent() {
        return parent;
    }

    /**
     * Get process input.
     * @return input
     */
    public StdIn getIn() {
        return in;
    }

    /**
     * Get process output.
     * @return output
     */
    public StdOut getOut() {
        return out;
    }

    /**
     * Get standard error stream.
     *
     * @return error stream
     */
    public StdErr getErr() {
        return err;
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
     * Command or process name.
     *
     * @return process name
     */
    public String getNameID() {
        return name ;
    }

    /**
     * Process owner.
     *
     * @return owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Return process unique ID
     *
     * @return PID
     */
    public long getPID() {
        return PID;
    }

    // </editor-fold>

    // <editor-fold desc="setters" defaultstate="collapsed">

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
     * Set parent of actual process.
     *
     * @param parent
     */
    public void setParent(Process parent) {
        this.parent = parent;
    }

    /**
     * Set process input.
     * @param in - new input
     */
    public void setIn(StdIn in) {
        this.in = in;
    }

    /**
     * Set process output.
     * @param out - new output
     */
    public void setOut(StdOut out) {
        this.out = out;
    }

    /**
     * Set standard error stream.
     *
     * @param err
     */
    public void setErr(StdErr err) {
        this.err = err;
    }

    /**
     * Set new working directory.
     * @param dest path for working directory
     */
    public void setWorkingDir(File dest) {
        this.workingDir = dest;
    }

    /**
     * Command or process name.
     *
     * @param process name
     */
    public void setNameID(String name) {
        this.name  = name;
    }

    /**
     * Owner of process.
     *
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Set process unique ID
     *
     * @param PID
     */
    public void setPID(long pid) {
        this.PID = pid;
    }

    // </editor-fold>

    // <editor-fold desc="other methods" defaultstate="collapsed">

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

    // </editor-fold>
}