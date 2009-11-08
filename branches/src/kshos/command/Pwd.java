/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import javax.swing.JTextArea;
import kshos.core.KSHprocess;

/**
 * PWD process.
 * Prints current working directory.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 8/11/2009
 */
public class Pwd extends KSHprocess {

    @Override
    public void run () {
        if (getOut() instanceof JTextArea) {
            ((JTextArea) getOut()).append("\nWorking directory: " + getParent().getWorkingDir());
        }
    }

}
