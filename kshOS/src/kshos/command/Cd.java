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

    @Override
    public void tick () {
        File newDir = null;
        if (getArgs() == null) this.getOut().stdAppend("Invalid parameter!");
        else {
            if (getArgs()[0].charAt(0) == '/') newDir = new File(getArgs()[0]);
            else newDir = new File(getParent().getWorkingDir() + File.separator + getArgs()[0]);
            if (!newDir.exists()) {
                this.getOut().stdAppend("\nNo such directory!");
                return;
            }
        }
        try {
            getParent().setWorkingDir(newDir.getCanonicalFile());
        } catch (IOException ex) {
            ex.printStackTrace();
            this.getOut().stdAppend("\nNo such directory!");
        }
        this.getOut().stdCloseOut();
        this.getParent().removeChild(this.getPID());
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
