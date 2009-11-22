/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kshos.command;

import java.io.File;
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

    @Override
    public void tick() {
        File thisDir = null;
        if (getArgs().length == 0 || getArgs()[0].equals("-l")) {
            thisDir = new File(getParent().getWorkingDir());
        } else {
            String path = getArgs()[getArgs().length - 1];
            if (path.charAt(0) == '/') {
                thisDir = new File(path);
            } else {
                thisDir = new File(getParent().getWorkingDir() + File.separator + path);
            }
            if (!thisDir.exists()) {
                this.getOut().stdAppend("No such directory!");
                return;
            }
        }
        for (int i = 0; i < thisDir.list().length; i++) {
            this.getOut().stdAppend(thisDir.list()[i] + "\n");
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
