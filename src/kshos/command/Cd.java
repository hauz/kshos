/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.IOException;
import javax.swing.JTextArea;
import kshos.core.KSHprocess;

/**
 * CD process.
 * Changes working directory to destination directory specified by first argument.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Cd extends KSHprocess {

    @Override
    public void run () {
        File newDir = null;
        if (getArgs() == null) ((JTextArea)getOut()).append("Invalid parameter!");
        else {
            if (getArgs()[0].charAt(0) == '/') newDir = new File(getArgs()[0]);
            else newDir = new File(getParent().getWorkingDir() + File.separator + getArgs()[0]);
            if (!newDir.exists()) {
                ((JTextArea)getOut()).append("\nNo such directory!");
                return;
            }
        }
        try {
            getParent().setWorkingDir(newDir.getCanonicalFile());
        } catch (IOException ex) {
            ex.printStackTrace();
            ((JTextArea)getOut()).append("\nNo such directory!");
        }
    }
}
