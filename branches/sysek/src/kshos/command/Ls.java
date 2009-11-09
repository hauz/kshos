/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kshos.command;

import java.io.File;
import javax.swing.JTextArea;
import kshos.core.KSHprocess;

/**
 * LS process.
 * Lists current/relative/absolute directory specified by last parameter.
 * Parameter -l prints full list. // not implemented yet
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Ls extends KSHprocess {

    @Override
    public void run() {
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
                ((JTextArea) getOut()).append("\nNo such directory!");
                return;
            }
        }
        // TODO: -l parameter
        for (int i = 0; i < thisDir.list().length; i++) {
            if (getOut() instanceof JTextArea) {
                ((JTextArea) getOut()).append("\n" + thisDir.list()[i]);
            }
        }
    }
}
