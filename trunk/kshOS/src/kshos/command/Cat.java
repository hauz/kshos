/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import kshos.core.KSHprocess;
import kshos.io.KSHReader;

/**
 * CAT command.
 * Reads and prints files specified by parameters.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.03 16/11/2009
 */
public class Cat extends KSHprocess {

    String file = "";

    private void fileIn(int fileCnt) {
        KSHReader read = null;
        String pom = "";

        for (int i = 0; i < fileCnt; i++) {
            // absolute/relative path
            if (getArgs()[i].charAt(0) == '/') pom = getArgs()[i];
            else pom = getParent().getWorkingDir() + File.separator + getArgs()[i];
            read = new KSHReader(pom);
            if(read.stdOpenIn()){
                while ((pom = read.stdReadln()) != null)
                    // moved EOL for file
                    file += pom + "\n";
                read.stdCloseIn();
            }
            else{
                file = "Cannot read " + getArgs()[i];
            }
            
        }
    }

    @Override
    public void run () {
        int len = getArgs().length;
        if (len != 0) {
            fileIn(len);
            // added EOL for console
            this.getOut().stdAppend("\n" + file);
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
                this.getOut().stdCloseOut();
                this.getParent().setChild(null);
                break;
            default:
                break;
        }
    }

}
