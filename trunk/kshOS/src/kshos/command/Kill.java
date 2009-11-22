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
    
    @Override
    public void tick () {
        int pid = -1;
        try {
            pid = Integer.parseInt(this.getArgs()[0]);
        } catch (NumberFormatException a) {
            this.getOut().stdWriteln("Invalid PID!");
        this.getParent().removeChild(this.getPID());
            return;
        }
        // TODO: uncomment when 'int killProcess(long PID)' implemented
        /*
        if (ProcessManager.instance().killProcess(pid) == 1) this.getOut().stdWriteln("No such process with PID " + pid + "!");
        else if (ProcessManager.instance().killProcess(pid) == 2) this.getOut().stdWriteln("Unable to kill process " + pid + "!");
        */
    }

    @Override
    public void processLine(String line) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processSignal(int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
