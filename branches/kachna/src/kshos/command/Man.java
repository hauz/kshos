/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import javax.swing.JTextArea;
import kshos.core.KSHprocess;

/**
 * MAN command.
 * Prints help and implemented commands.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Man extends KSHprocess {

    private static final String MAN = "\nMAN page for kshOS Virtual Machine Manager\n" +
            "Usage: command [ params ] [ < file_input ] [ | command params ] [ > file_output ]" +
            "\nImplemanted commands:\n" +
            "man - this help\n" +
            "cd param - change directory to param\n" +
            "pwd - prints current directory\n" +
            "ls [param] - list current/param folder\n" +
            "echo [params] - prints params\n" +
            "cat [params] - reads params specified files, merge this files";
    
    @Override
    public void run () {
        this.getOut().stdAppend(MAN);
        this.getOut().stdCloseOut();
        this.getParent().setChild(null);
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
