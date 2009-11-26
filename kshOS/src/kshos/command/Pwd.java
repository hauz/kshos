package kshos.command;

import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;

/**
 * PWD process.
 * Prints current working directory.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Pwd extends Process {

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
    public void tick () {
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("PWD_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else {
            this.getOut().stdWriteln("Working directory: " + this.getWorkingDir());
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
