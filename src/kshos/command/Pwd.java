/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

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
        this.getOut().stdAppend("\nWorking directory: " + getParent().getWorkingDir());
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
