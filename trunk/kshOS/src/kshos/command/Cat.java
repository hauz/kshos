/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import kshos.core.objects.Process;
import kshos.io.KSHReader;
import kshos.io.KSHWriter;

/**
 * CAT command.
 * Reads and prints files specified by parameters.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.03 16/11/2009
 */
public class Cat extends Process {

    /**
     * Global var 'file'.
     */
    private String file = "";

    /**
     * File input.
     * Combines text from all specified (as command arguments) input files
     * to global var 'file'.
     * @param fileCnt number of input files (argument count)
     */
    private void fileIn(int fileCnt) {
        KSHReader read = null;
        String pom = "";

        for (int i = 0; i < fileCnt; i++) {
            read = new KSHReader(getArgs()[i], getParent().getWorkingDir());
            if(read.stdOpenIn()){
                while ((pom = read.stdReadln()) != null)
                    // moved EOL for file
                    file += pom + "\n";
                read.stdCloseIn();
            }
            else{
                this.getErr().stdWriteln("Cannot read " + getArgs()[i]);
            }
            
        }
    }

    /**
     * Process main function.
     */
    @Override
    public void tick () {
        int len = getArgs().length;
        // when has arguments
        if (len != 0) {
            fileIn(len);
            this.getOut().stdWriteln(file);
            this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
        }
        // when gets another then console input 'cat < smth'
        if (getIn().toString().indexOf("UserInterface") < 0) {
         
            // check if output is the same as input
            KSHWriter out = (KSHWriter)this.getOut();
            KSHReader in = (KSHReader)this.getIn();
            if(out.getPath().equals(in.getPath())){
                this.getErr().stdWriteln("Input file is output file");
                this.getOut().stdCloseOut();
                this.getIn().stdCloseIn();
                this.processSignal(0);
            }

            String pom = "";
            file = "";
            while ((pom = getIn().stdReadln()) != null)
                    // moved EOL for file
                    file += pom + "\n";
            this.getOut().stdAppend(file);
            this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
        }
    }

    /**
     * Line processing.
     * Aplies for console input when no input file specified.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getOut().stdAppend(line + "\n");
    }

    /**
     * Signal processing.
     * @param type signal type
     */
    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getOut().stdCloseOut();
                // put all children to new parent
                while (this.getAllChilds().size() > 0) {
                    this.getParent().addChild(this.getChild(this.getAllChilds().firstKey()));
                    this.removeChild(this.getAllChilds().firstKey());
                }
                this.getParent().removeChild(this.getPID());
                break;
            default:
                break;
        }
    }

}