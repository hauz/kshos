package kshos.command;

import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;

/**
 * MAN command.
 * Prints help and implemented commands.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Man extends Process {

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
     *
     * Updated by hauz - 25.11.2009
     */
    @Override
    public void tick () {
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("MAN_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else this.getOut().stdAppend(Core.instance().getProperties().getProperty("MAN_MSG"));
        close();
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdAppend("Cannot process line!");
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
                    this.getChild(this.getAllChilds().firstKey()).setParent(this.getParent());
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
