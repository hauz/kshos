/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import javax.swing.JTextArea;
import kshos.core.objects.Process;

/**
 * ECHO command.
 * Prints params of echo commands.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Echo extends Process {

    @Override
    public void tick () {
        String s = "\n";
        int len = getArgs().length;
        for (int i = 0; i < len; i++) {
            s += getArgs()[i];
            if (i != len-1) s += " ";
        }
        this.getOut().stdAppend(s);
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
