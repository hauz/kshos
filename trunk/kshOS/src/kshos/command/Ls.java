package kshos.command;

import java.io.File;
import kshos.core.Core;
import kshos.core.objects.Process;

/**
 * LS process.
 * Lists current/relative/absolute directory specified by last parameter.
 * Parameter -l prints full list. // not implemented yet
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 11/11/2009
 */
public class Ls extends Process {

    /**
     * Process main function.
     */
    @Override
    public void tick() {
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("LS_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
            this.getParent().removeChild(this.getPID());
            return;
        }
        File thisDir = null;
        if (getArgs().length == 0) {
            thisDir = new File(getParent().getWorkingDir());
        } else {
            String path = getArgs()[getArgs().length - 1];
            if (path.charAt(0) == '/') {
                thisDir = new File(path);
            } else {
                thisDir = new File(getParent().getWorkingDir() + File.separator + path);
            }
            if (!thisDir.exists()) {
                this.getErr().stdAppend("No such directory!");
                this.getParent().removeChild(this.getPID());
                return;
            }
        }
        for (int i = 0; i < thisDir.list().length; i++) {
            this.getOut().stdAppend(thisDir.list()[i] + "\n");
        }
        this.getOut().stdCloseOut();
        this.getParent().removeChild(this.getPID());
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdAppend("Cannot process line!");
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
