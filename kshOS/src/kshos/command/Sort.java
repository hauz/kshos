/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.util.ArrayList;
import java.util.Arrays;
import kshos.core.objects.Process;
import kshos.io.KSHReader;

/**
 * SORT command.
 * Sort file or entered lines.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 16/11/2009
 */
public class Sort extends Process {

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
        String line = "";
        KSHReader read = null;

        for (int i = 0; i < fileCnt; i++) {
            read = new KSHReader(getArgs()[i], getParent().getWorkingDir());
            if(read.stdOpenIn()){
                while ((line = read.stdReadln()) != null)
                    lines.add(line);
                read.stdCloseIn();
            }
            else{
                this.getOut().stdWriteln("Cannot read " + getArgs()[i]);
                //TODO: terminate this process
            }
        }
    }

    @Override
    public void tick () {
        int len = getArgs().length;
        if (len != 0) {
            fileIn(len);
            this.getOut().stdAppend(sort(lines.toArray()) + "\n");
            this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
        }
        if (getIn().toString().indexOf("UserInterface") < 0) {
            String pom = "";
            lines = new ArrayList<String>();
            while ((pom = getIn().stdReadln()) != null)
                    lines.add(pom);
            this.getOut().stdAppend(sort(lines.toArray()) + "\n");
            this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
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
                this.getOut().stdCloseOut();
                this.getParent().removeChild(this.getPID());
                break;
            default:
                break;
        }
    }

}
