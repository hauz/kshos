package kshos.command;

import java.io.File;
import java.io.IOException;
import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;

/**
 * CD process.
 * Changes working directory to destination directory specified by first argument.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Cd extends Process {

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
        File newDir = null;
        if (getArgs().length == 0) {
            this.getErr().stdAppend("Invalid parameter!");
        } // if has params
        else if (getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("CD_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else {
            if (getArgs()[0].charAt(0) == '/') {
                newDir = new File(getArgs()[0]);
            } else {
                newDir = new File(this.getWorkingDir() + File.separator + getArgs()[0]);
            }
            if (newDir.exists()) {
                try {
                    getParent().setWorkingDir(newDir.getCanonicalFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    this.getErr().stdAppend("No such directory!");
                }
            } else {
                this.getErr().stdAppend("No such directory!");
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
        this.getErr().stdAppend("Cannot process line!");
        this.processSignal(0);
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
