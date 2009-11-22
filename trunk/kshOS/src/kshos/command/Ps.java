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

    @Override
    public void tick () {
        // TODO: uncomment when getProcessList() implemented
        ArrayList<Process> list = null;// = ProcessManager.instance().getProcessList();
        for (int i = 0; i < list.size(); i++) {
            this.getOut().stdAppend(list.get(i).getPID() + "\t" + list.get(i).getName());
        }
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
