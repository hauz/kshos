package kshos.core.objects;

import java.util.TreeMap;

/**
 * Class represents one single process.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 5.11.2009
 */
public abstract class Process extends Thread {

    // <editor-fold defaultstate="collapsed" desc="Main variables and constants.">

    /** Process ID - unique number to identify the process */
    protected long PID = 0;
    
    /** Parrent process handler */
    private Process parrent = null;

    /** Childs handler. */
    protected TreeMap<Long, Process> childTree = null;

    /** Input stream. */
    // TODO: add input stream

    /** Output stream. */
    // TODO: add output stream

    // </editor-fold>

    // TODO: implement all attributes and all methods

    /**
     * This method is similar to Threads method run().
     */
    public abstract void tick();

    /**
     * This method is used to free all allocated resource.
     */
    public abstract void kill();

    /**
     * This method is equivalent of toString() method.
     * It is used to get help message of actual process.
     *
     * @return help message
     */
    public abstract String getHelp();

    @Override
    public void run() {
        // TODO: implement run method of the thread

        tick();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    /**
     * Add new child into tree.
     *
     * @param child
     */
    protected void addChild(Process child) {
        this.childTree.put(child.getPID(), child);
    }

    /**
     * Remove child from tree.
     *
     * @param child
     */
    protected void removeChild(Process child) {
        this.childTree.remove(child.getPID());
    }

    /**
     * Return the parent of actual process.
     *
     * @return parent
     */
    public Process getParrent() {
        return parrent;
    }

    /**
     * Set parent of actual process.
     *
     * @param parrent
     */
    public void setParrent(Process parrent) {
        this.parrent = parrent;
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

    // </editor-fold>

}
