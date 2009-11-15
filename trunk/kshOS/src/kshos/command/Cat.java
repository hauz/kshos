/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JTextArea;
import kshos.core.KSHprocess;
import kshos.io.KSHReader;

/**
 * CAT command.
 * Reads and prints files specified by parameters.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Cat extends KSHprocess {

    String file = "";

    private void fileIn(int fileCnt) {
        KSHReader read = null;
        String pom = "";

        for (int i = 0; i < fileCnt; i++) {
            read = new KSHReader(getParent().getWorkingDir() + File.separator + getArgs()[i]);
            while ((pom = read.stdReadln()) != null)
                file += "\n" + pom;
            read.stdCloseIn();
        }
    }

    @Override
    public void run () {
        int len = getArgs().length;
        if (len != 0) {
            fileIn(len);
            this.getOut().stdAppend(file);
            this.getOut().stdCloseOut();
            this.getParent().setChild(null);
        }
    }

    @Override
    public void processLine(String line) {
        this.getOut().stdAppend(line + "\n");
    }

    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getParent().setChild(null);
                break;
            default:
                break;
        }
    }

}
