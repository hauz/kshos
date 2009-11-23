/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.util.ArrayList;
import kshos.core.objects.Process;
import kshos.core.ProcessManager;

/**
 * PS command.
 * Prints list of running processes.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 19/11/2009
 */
public class Ps extends Process {

    /**
     * Process main function.
     */
    @Override
    public void tick () {
        // TODO: uncomment when getProcessList() implemented
        ArrayList<Process> list = null;// = ProcessManager.instance().getProcessList();
        for (int i = 0; i < list.size(); i++) {
            this.getOut().stdWriteln(list.get(i).getPID() + "\t" + list.get(i).getName());
        }
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
