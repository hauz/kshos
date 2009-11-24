/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.util.ArrayList;
import kshos.core.objects.Process;
import kshos.core.ProcessManager;
import kshos.core.objects.MetaProcess;

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
        ArrayList<MetaProcess> list = ProcessManager.instance().getProcessList();
        if (this.getArgs().length != 0 && this.getArgs()[0].equals("-u")) {
            this.getOut().stdWriteln("PID\tProcess");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUser().equals(this.getOwner().getUserName()))
                    this.getOut().stdWriteln(list.get(i).getPID() + "\t" + list.get(i).getName());
            }
        } else {
            this.getOut().stdWriteln("PID\tUser\tProcess");
            for (int i = 0; i < list.size(); i++) {
                this.getOut().stdWriteln(list.get(i).toString());
            }
        }
        this.getParent().removeChild(this.getPID());
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
