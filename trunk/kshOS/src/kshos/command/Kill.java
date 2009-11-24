/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

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
    public void tick () {
        long pid = -1;
        try {
            pid = Integer.parseInt(this.getArgs()[0]);
        } catch (NumberFormatException a) {
            this.getErr().stdWriteln("Invalid PID!");
            this.getParent().removeChild(this.getPID());
            return;
        }

        if (ProcessManager.instance().getProcess(pid) == null) {
            this.getErr().stdWriteln("No such process with PID " + pid + "!");
        } else ProcessManager.instance().getProcess(pid).processSignal(0);  // TODO: running
        this.getParent().removeChild(this.getPID());
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdWriteln("Cannot process line!");
        this.getParent().removeChild(this.getPID());
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
                break;
            default:
                break;
        }
    }

}
