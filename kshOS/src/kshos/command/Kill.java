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
            this.getOut().stdWriteln("Invalid PID!");
            this.getParent().removeChild(this.getPID());
            return;
        }

        if (ProcessManager.instance().getProcess(pid) == null) {
            this.getOut().stdWriteln("No such process with PID " + pid + "!");
            this.getParent().removeChild(this.getPID());
        }
        else ProcessManager.instance().getProcess(pid).processSignal(0);
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getOut().stdAppend("Cannot process line!");
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
                this.getParent().removeChild(this.getPID());
                break;
            default:
                break;
        }
    }

}
