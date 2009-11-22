package kshos.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import kshos.command.grammar.OSVM_grammarParser;
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
     * Create new shell window and window maintaninig process.
     * All necessary objects are set and liked together.
     *
     * @param window label
     * @return maintaining process
     */
    public Process createShell(String label) {
//        UserInterface console = UIManager.instance().newConsole(label);
        // TODO: create new process

        return null;        // TODO: add real value instead of MOCK
    }

    /**
     * Return object Process with specifi PID
     * 
     * @param PID
     * @return Process or null when not such a process
     */
    public Process getProcess(long PID) {
        for (Process process: processList) {
            if (process.getPID() == PID) {
                return process;
            }
        }

        return null;
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
            public void tick() { }

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
                    case 0: this.running = false;
                        break;
                }
            }

        };
        
        // set process PID
        init.setPID(1);
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
     * @param owner
     * @param parent
     * @param userInterface
     * @param g
     */
    public synchronized void createProcess(String command, User owner, 
            Process parent, UserInterface userInterface, OSVM_grammarParser g) {

        // create instance of new process
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        Process cmd = null;
        try {
            cmd = (Process) loader.loadClass("kshos.command." + command).newInstance();
        } catch (Exception ex) {
            parent.getOut().stdAppend("\nInvalid command!");
            return;
        }

        cmd.setArgs(g.getCmdTable().get(g.getCmdTable().size() - 1)); // set process parameters
        parent.addChild(cmd);           // add this process as parents child
        cmd.setParent(parent);          // set this process's parent

        // set process input
        if (g.getIn() == null) {
            cmd.setIn(userInterface);
        } else {
            cmd.setIn(new KSHReader(g.getIn(), parent.getWorkingDir()));
        }
        if(!cmd.getIn().stdOpenIn()) {
            cmd.processSignal(0);
            parent.getOut().stdAppend("\nCannot read " + g.getIn());
            return;
        }

        // set process output
        if (g.getOut() == null) {
            cmd.setOut(userInterface);
        } else {
            cmd.setOut(new KSHWriter(g.getOut(), parent.getWorkingDir()));
        }
        if(!cmd.getOut().stdOpenOut()) {
            cmd.processSignal(0);
            parent.getOut().stdAppend("\nCannot write " + g.getOut());
            return;
        }
        
        // start process and waait for its execution
        cmd.start();
        try {
            cmd.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}