package kshos.command;

import java.util.ArrayList;
import kshos.core.Core;
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
     * Close method.
     * Closes input and output, removes process from process list
     * and from parents children process tree.
     */
    private void close() {
        this.getIn().stdCloseIn();
        this.getOut().stdCloseOut();
        this.getParent().removeChild(this.getPID());
        ProcessManager.instance().removeProcess(this.getPID());
    }

    /**
     * Process main function.
     */
    @Override
    public void tick() {
        ArrayList<MetaProcess> list = ProcessManager.instance().getProcessList();

        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("PS_HLP"));
            } else if (getArgs()[0].charAt(1) == 'u') {
                this.getOut().stdWriteln("PID\tProcess");
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUser().equals(this.getOwner().getUserName())) {
                        this.getOut().stdWriteln(list.get(i).getPID() + "\t" + list.get(i).getName());
                    }
                }
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else {
            this.getOut().stdWriteln("PID\tUser\tProcess");
            for (int i = 0; i < list.size(); i++) {
                this.getOut().stdWriteln(list.get(i).toString());
            }
        }
        close();
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getOut().stdAppend("Cannot process line!");
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
                // put all children to new parent
                while (this.getAllChilds().size() > 0) {
                    this.getParent().addChild(this.getChild(this.getAllChilds().firstKey()));
                    this.removeChild(this.getAllChilds().firstKey());
                }
                close();
                break;
            default:

                break;
        }
    }
}
