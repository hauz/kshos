package kshos.command;

import java.util.ArrayList;
import java.util.Arrays;
import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;
import kshos.io.KSHReader;
import kshos.io.Pipe;
import kshos.ui.UserInterface;

/**
 * SORT command.
 * Sort file or entered lines.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 16/11/2009
 */
public class Sort extends Process {

    /**
     * Global arraylist 'lines'.
     */
    private ArrayList<String> lines = new ArrayList<String>();

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
     * Sort function.
     * Sorts array of objects using Arrays.sort.
     * Puts result to String array
     * @param array unsorted lines
     * @return String array of sorted items
     */
    private String sort(Object[] array) {
        String file = "";
        if (array.length > 0) {
            Arrays.sort(array);
            for (int j = 0; j < array.length - 1; j++) {
                file += (String) array[j] + "\n";
            }
            file += (String) array[array.length - 1];
        }
        return file;
    }

    /**
     * File input.
     * Combines lines from all specified (as command arguments) input files
     * to global arraylist 'lines'.
     * @param fileCnt number of input files (argument count)
     */
    private void fileIn(int fileCnt) {
        String line = "";
        KSHReader read = null;

        for (int i = 0; i < fileCnt; i++) {
            read = new KSHReader(getArgs()[i], this.getWorkingDir());
            if (read.stdOpenIn()) {
                while ((line = read.stdReadln()) != null) {
                    lines.add(line);
                }
                read.stdCloseIn();
            } else {
                this.getErr().stdWriteln("Cannot read " + getArgs()[i]);
            }
        }
    }

    /**
     * Process main function.
     */
    @Override
    public void tick() {
        int len = getArgs().length;
        if (len != 0) {
            if (getArgs()[0].charAt(0) == '-') {
                if (getArgs()[0].charAt(1) == 'h') {
                    this.getOut().stdWriteln(Core.instance().getProperties().getProperty("SORT_HLP"));
                } else {
                    this.getErr().stdWriteln("Bad parameter!");
                }
                close();
                return;
            }
            fileIn(len);
            this.getOut().stdAppend(sort(lines.toArray()));
            if (getOut() instanceof UserInterface) this.getOut().stdWriteln("");
            if (getIn() instanceof UserInterface) this.getOut().stdCloseOut();
            this.getParent().removeChild(this.getPID());
            ProcessManager.instance().removeProcess(this.getPID());
        }
        if (!(getIn() instanceof UserInterface)) {
            String pom = "";
            lines = new ArrayList<String>();
            while ((pom = getIn().stdReadln()) != null) {
                lines.add(pom);
            }
            this.getOut().stdAppend(sort(lines.toArray()));
            if (getOut() instanceof UserInterface) this.getOut().stdWriteln("");
            close();
        }
        if ((getIn() instanceof UserInterface) && (getOut() instanceof Pipe)) {
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
        lines.add(line);
    }

    /**
     * Signal processing.
     * @param type signal type
     */
    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getOut().stdAppend(sort(lines.toArray()));
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
