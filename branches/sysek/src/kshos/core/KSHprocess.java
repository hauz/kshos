/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import kshos.io.StdIn;
import kshos.io.StdOut;

/**
 * Simple process.
 * Replace by final version of process.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 11/11/2009
 */
public class KSHprocess extends Thread {

    private StdIn in;
    private StdOut out;
    private File workingDir;
    private String[] args;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(ArrayList<String> arg) {
        args = new String[arg.size() - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = arg.get(i);
        }
    }
    private KSHprocess parent;
    private KSHprocess child;

    public KSHprocess getChild() {
        return child;
    }

    public void setChild(KSHprocess child) {
        this.child = child;
    }

    public KSHprocess getParent() {
        return parent;
    }

    public void setParent(KSHprocess parent) {
        this.parent = parent;
    }

    public Object getIn() {
        return in;
    }

    public void setIn(StdIn in) {
        this.in = in;
    }

    public StdOut getOut() {
        return out;
    }

    public void setOut(StdOut out) {
        this.out = out;
    }

    public String getWorkingDir() {
        try {
            return this.workingDir.getCanonicalPath();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error!";
        }
    }

    public void setWorkingDir(File dest) {
        this.workingDir = dest;
    }

}
