package kshos.core;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import kshos.core.objects.User;
import kshos.ui.UIManager;
import kshos.ui.UserInterface;

/**
 * Process management class.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 1.11.2009
 */
public class ProcessManager {

    private ArrayList<Process> processList = null;
    private long PIDCounter;
    // <editor-fold defaultstate="collapsed" desc="Singleton implementation">
    private static ProcessManager instance = null;

    /**
     * Initial constructor.
     */
    protected ProcessManager() {
        initialize();
    }

    /**
     * Destroy actual instance.
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Actual class instance.
     *
     * @return instance
     */
    public static ProcessManager instance() {

        synchronized (ProcessManager.class) {
            if (instance == null) {
                instance = new ProcessManager();
            }
        }

        return instance;
    }
    // </editor-fold>

    /**
     * Initializes all necessary.
     * Called from constructor.
     */
    private void initialize() {
        this.processList = new ArrayList<Process>();
        this.PIDCounter = 2;
    }

    /**
     * Unique process indicator.
     *
     * @return
     */
    private long getPID() {
        // TODO: create smarter PID generator. Generate random XXX sequence
        // which will be saved in special pid-list. Everytimes when new pid is
        // generated the pid-list will be checked for its existence.

        return PIDCounter;
    }

    /**
     * Increases PID
     */
    private void incPID() {
        PIDCounter++;
    }

    /**
     * Create new shell window and window maintaninig process.
     * All necessary objects are set and liked together.
     *
     * @param window label
     * @return maintaining process
     */
    public Process createShell(String label) {
        UserInterface console = UIManager.instance().newConsole(label);
        // TODO: create new process

        return null;        // TODO: add real value instead of MOCK
    }

    public Process createProcess(String command, User owner, Process parent) {
        // TODO: finish method create process

        Process proc = null;

        try {

        } catch (Exception ex) {
            // TODO: print error message to standard output
        }


        return null;        // TODO: change MOCK for real value
    }
}
