/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.IOException;
import kshos.core.objects.Process;

/**
 * CD process.
 * Changes working directory to destination directory specified by first argument.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Cd extends Process {

    /**
     * Process main function.
     */
    @Override
    public void tick () {
        File newDir = null;
        if (getArgs().length == 0) this.getErr().stdAppend("Invalid parameter!");
        else {
            if (getArgs()[0].charAt(0) == '/') newDir = new File(getArgs()[0]);
            else newDir = new File(getParent().getWorkingDir() + File.separator + getArgs()[0]);
            if (!newDir.exists()) {
                this.getErr().stdAppend("No such directory!");
                this.getParent().removeChild(this.getPID());
                return;
            }
            try {
                getParent().setWorkingDir(newDir.getCanonicalFile());
            } catch (IOException ex) {
                ex.printStackTrace();
                this.getErr().stdAppend("No such directory!");
            }
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
