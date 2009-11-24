package kshos.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import kshos.command.KSHell;
import kshos.command.grammar.OSVM_grammarParser;
import kshos.core.objects.MetaProcess;
import kshos.core.objects.User;
import kshos.core.objects.Process;
import kshos.io.KSHReader;
import kshos.io.KSHWriter;
import kshos.ui.UserInterface;

/**
 * Process management class.
 * Each process has its unique PID (Process ID) number. PID function is simple
 * ascending function f(x+1) = f(x) + 1. That means we could presume that
 * processes with less PID are older than the bigger ones.
 * This thesis is used in whole program and acts as simple "planning algorithm".
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
     * Collection with basic process information.
     * This information could be used for ps or top command.
     *
     * Information returned via MetaProcess:
     *      * PID
     *      * User name
     *      * Process name
     *
     * @return collection of metaprocesses
     */
    public ArrayList<MetaProcess> getProcessList() {
        ArrayList<MetaProcess> metaProcesses = new ArrayList<MetaProcess>();

        // transform process collection into metaprocess collection
        for (Process proc : this.processList) {
            metaProcesses.add(new MetaProcess(proc.getOwner().getUserName(),
                    proc.getName(), proc.getPID()));
        }

        return metaProcesses;
    }

    /**
     * Return object Process with specifi PID
     *
     * @param PID
     * @return Process or null when not such a process
     */
    public Process getProcess(long PID) {
        for (Process process : processList) {
            if (process.getPID() == PID) {
                return process;
            }
        }

        return null;
    }

    /**
     * Method removes process from process list.
     *
     * @param PID
     */
    public void removeProcess(long PID) {

        // removes process from process list
        for (Process proc: this.processList) {
            if (proc.getPID() == PID) {
                this.processList.remove(proc);
                proc = null;
                return;
            }
        }
    }

    /**
     * Removes all processes created by specified user.
     *
     * @param user
     */
    public void removeAllUserProcesses(User user) {

        // removes all processes where owner == user
        for (Process proc: this.processList) {
            if (proc.getOwner() == user) {
                this.processList.remove(proc);
                proc = null;
            }
        }

    }

    /**
     * Metods return the last created shell in process list.
     * We use the main presumption used during process creation. That says
     * we have simple growing function to count PID of new process. That means
     * that each new process will have PID bigger than the oder ones processes.
     * That means we are looking for shell with biggest PID because this one
     * is the last created.
     *
     * @return latest shell
     */
    public KSHell getLastShell(User owner) {
        
        ArrayList<Process> shellList = new ArrayList<Process>();
        KSHell lastCreated = null;
        
        // find all shells in process list
        for (Process proc: this.processList) {
            if (proc instanceof KSHell && proc.getOwner().equals(owner)) {
                shellList.add(proc);
            }
        }

        // If we have fount at least one shell in process list then
        // find the shell with the biggest PID. We coudl presume that
        // the biggest PID is the last one created.
        if (shellList.size() != 0) {
            lastCreated = (KSHell) shellList.get(0);

            for (Process proc: shellList) {
                if (lastCreated.getPID() < proc.getId()) {
                    lastCreated = (KSHell) proc;
                }
            }
        }

        return lastCreated;
    }

    /**
     * Create new shell and sets its parameters.
     * Then after shell initialization, the shell is started.
     *
     * @param user interface abowe the shell
     * @return maintaining process
     */
    public void createShell(UserInterface ui, long parentPID) {
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        KSHell shell = null;

        try {
            // create new instance of shell
            shell = (KSHell) loader.loadClass("kshos.command.KSHell").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // set shell parameters and start it
        shell.setUserInterface(ui);
        shell.setPID(getPID());
        incPID();
        shell.setName("kshell");
        shell.setOwner(ui.getUser());
        getProcess(parentPID).addChild(shell);
        shell.setParent(getProcess(parentPID));
        this.processList.add(shell);
        shell.start();
    }

    /**
     * Init proces is the highest process in process tree.
     * In main implementation does nothing. But this process is
     * generally used to run once or maintain periodical operations
     * in operating system.
     *
     * @return INIT process
     */
    public synchronized Process createInitProcess() {

        // create new process
        Process init = new Process() {
            // running flag used for thread stoping

            private boolean running;

            @Override
            public void processLine(String line) {
                // not implemented
            }

            @Override
            public void tick() {
            }

            /**
             * Singals for thread.
             *
             * Avilable signals:
             *      0       thread stop
             */
            @Override
            public void processSignal(int type) {
                switch (type) {
                    // thread stop
                    case 0:
                        this.running = false;
                        break;
                }
            }
        };

        // set process PID
        init.setPID(1);
        // set user
        init.setOwner(new User("Core"));
        // set process name
        init.setName("INIT");
        // add process into process list
        this.processList.add(init);

        return init;
    }

    /**
     * Creates new process with specific parameters.
     * After process creation the process is executed and the main
     * thread is waiting for its finish.
     *
     * @param command
     * @param parent
     * @param userInterface
     * @param g
     */
    public synchronized void createProcess(String command, Process parent,
            UserInterface ui, OSVM_grammarParser g) {

        // formal parameters test
        if ((command == null) || command.trim().equals("") || (ui.getUser() == null) ||
                (parent == null) || (ui == null) || (g == null)) {
            return;
        }

        // create instance of new process
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        Process cmd = null;
        try {
            cmd = (Process) loader.loadClass("kshos.command." + command).newInstance();
        } catch (Exception ex) {
            parent.getOut().stdWriteln("Invalid command!");
            return;
        }

        // set process parameters
        cmd.setArgs(g.getCmdTable().get(g.getCmdTable().size() - 1)); // set process parameters
        cmd.setPID(getPID());                // set process PID
        incPID();                            // count new PID
        parent.addChild(cmd);                // add this process as parents child
        cmd.setParent(parent);               // set this process's parent
        cmd.setOwner(ui.getUser());                 // set process owner (user)
        cmd.setName(command.toLowerCase());  // set process name

        cmd.setErr(ui);                      // set error input
        cmd.getErr().stdOpenOut();

        // set process input
        if (g.getIn() == null) {
            cmd.setIn(ui);
        } else {
            cmd.setIn(new KSHReader(g.getIn(), parent.getWorkingDir()));
        }
        if (!cmd.getIn().stdOpenIn()) {
            cmd.getErr().stdWriteln("Cannot read " + g.getIn());
            cmd.processSignal(0);            
            return;
        }

        // set process output
        if (g.getOut() == null) {
            cmd.setOut(ui);
        } else {
            cmd.setOut(new KSHWriter(g.getOut(), parent.getWorkingDir()));
        }
        if (!cmd.getOut().stdOpenOut()) {
            cmd.getErr().stdWriteln("Cannot write " + g.getOut());
            cmd.processSignal(0);            
            return;
        }

        // start process and waait for its execution
        cmd.start();
        try {
            cmd.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            processList.remove(cmd);
        }
    }
}
