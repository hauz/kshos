package kshos.command;

import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;
import kshos.io.KSHReader;
import kshos.ui.UserInterface;

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
     * Close method.
     * Closes input and output, removes process from process list
     * and from parents children process tree.
     */
    private void close() {
        if (this.getIn() != null) this.getIn().stdCloseIn();
        if (this.getOut() != null) this.getOut().stdCloseOut();
        this.getParent().removeChild(this.getPID());
        ProcessManager.instance().removeProcess(this.getPID());
    }

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
            read = new KSHReader(getArgs()[i], this.getWorkingDir());
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
            // if has params
            if (getArgs()[0].charAt(0) == '-') {
                if (getArgs()[0].charAt(1) == 'h') {
                    this.getOut().stdWriteln(Core.instance().getProperties().getProperty("CAT_HLP"));
                } else {
                    this.getErr().stdWriteln("Bad parameter!");
                }
                close();
                return;
            }
            fileIn(len);
            this.getOut().stdAppend(file);
            if (getOut() instanceof UserInterface) this.getOut().stdWriteln("");
            if (getIn() instanceof UserInterface) this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
            ProcessManager.instance().removeProcess(this.getPID());
        }
        // when gets another then console input 'cat < smth'
        if (!(getIn() instanceof UserInterface)) {
            String pom = "";
            file = "";
            while ((pom = getIn().stdReadln()) != null)
                    // moved EOL for file
                    file += pom + "\n";
            this.getOut().stdAppend(file);
            close();
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
                // put all children to new parent
                while (this.getAllChilds().size() > 0) {
                    this.getChild(this.getAllChilds().firstKey()).setParent(this.getParent());
                    this.getParent().addChild(this.getChild(this.getAllChilds().firstKey()));
                    this.removeChild(this.getAllChilds().firstKey());
                }
                close();
                break;
            default:
                break;
        }
    }  
}
