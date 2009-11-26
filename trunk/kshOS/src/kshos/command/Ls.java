package kshos.command;

import java.io.File;
import kshos.core.Core;
import kshos.core.ProcessManager;
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
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("LS_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else {
            File thisDir = null;
            if (getArgs().length == 0) {
                thisDir = new File(this.getWorkingDir());
            } else {
                String path = getArgs()[getArgs().length - 1];
                if (path.charAt(0) == '/') {
                    thisDir = new File(path);
                } else {
                    thisDir = new File(this.getWorkingDir() + File.separator + path);
                }
                if (!thisDir.exists()) {
                    this.getErr().stdAppend("No such directory!");
                    close();
                    return;
                }
            }
            String fileList = "";
            for (int i = 0; i < thisDir.list().length; i++) {
                // print directory first
                if (thisDir.listFiles()[i].isDirectory()) {
                    this.getOut().stdWriteln("D\t" + thisDir.list()[i]);
                } // store files
                else {
                    fileList += "F\t" + thisDir.list()[i] + "\n";
                }
            }
            // print files
            this.getOut().stdAppend(fileList);
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
