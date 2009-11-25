package kshos.command;

import kshos.core.Core;
import kshos.core.objects.Process;
import kshos.core.ProcessManager;

/**
 * KILL command.
 * Kill process by PID.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 19/11/2009
 */
public class Kill extends Process {

    /**
     * Process main function.
     */
    @Override
    public void tick() {
        long pid = -1;
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("KILL_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
            this.getParent().removeChild(this.getPID());
            return;
        } else {
            try {
                pid = Integer.parseInt(this.getArgs()[0]);
            } catch (NumberFormatException a) {
                this.getErr().stdWriteln("Invalid PID!");
                this.getParent().removeChild(this.getPID());
                ProcessManager.instance().removeProcess(this.getPID());
                return;
            }
        }

        if (ProcessManager.instance().getProcess(pid) == null) {
            this.getErr().stdWriteln("No such process with PID " + pid + "!");
        } else {
            // print line header before process exit
            if (pid != 1) ProcessManager.instance().getProcess(pid).getOut().stdAppend(
                    ProcessManager.instance().getProcess(pid).getOwner().getUserName() + "> ");
            ProcessManager.instance().getProcess(pid).processSignal(0);
        }
        this.getParent().removeChild(this.getPID());
        ProcessManager.instance().removeProcess(this.getPID());
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdWriteln("Cannot process line!");
        processSignal(0);
    }

    /**
     * Signal processing.
     * @param type signal type
     */
    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getOut().stdCloseOut();
                // put all children to new parent
                while (this.getAllChilds().size() > 0) {
                    this.getParent().addChild(this.getChild(this.getAllChilds().firstKey()));
                    this.removeChild(this.getAllChilds().firstKey());
                }
                this.getParent().removeChild(this.getPID());
                ProcessManager.instance().removeProcess(this.getPID());
                break;
            default:
                break;
        }
    }
}
