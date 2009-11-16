/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import kshos.core.KSHprocess;
import kshos.io.KSHReader;

/**
 * SORT command.
 * Sort file or entered lines.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Sort extends KSHprocess {

    private ArrayList<String> lines = new ArrayList<String>();

    private String sort(Object[] array) {
        String file = "";
        Arrays.sort(array);
        for (int j = 0; j < array.length - 1; j++) {
            file += (String) array[j]  + "\n";
        }
        file += (String) array[array.length - 1];
        return file;
    }

    private void fileIn(int fileCnt) {
        String pom = "";
        KSHReader read = null;

        for (int i = 0; i < fileCnt; i++) {
            read = new KSHReader(getParent().getWorkingDir() + File.separator + getArgs()[i]);
            while ((pom = read.stdReadln()) != null)
                lines.add(pom);
            read.stdCloseIn();
        }
    }

    @Override
    public void run () {
        int len = getArgs().length;
        if (len != 0) {
            fileIn(len);
            this.getOut().stdAppend("\n" + sort(lines.toArray()));
            this.getOut().stdCloseOut();
            this.getParent().setChild(null);
        }
    }

    @Override
    public void processLine(String line) {
        lines.add(line);
    }

    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getOut().stdAppend(sort(lines.toArray()));
                this.getParent().setChild(null);
                break;
            default:
                break;
        }
    }

}