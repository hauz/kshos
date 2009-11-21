/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kshos.command;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import kshos.command.grammar.*;
import kshos.core.KSHprocess;
import kshos.io.*;
import kshos.ui.UserInterface;
import org.antlr.runtime.*;

/**
 * Shell.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.03 15/11/2009
 */
public class KSHell extends KSHprocess {

    private UserInterface userInterface;
    private int commandIndex;
    private ArrayList<String> commandHistory;

    public int getCommandIndex() {
        return commandIndex;
    }

    public void incCommandIndex() {
        commandIndex++;
    }

    public void decCommandIndex() {
        commandIndex--;
    }

    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }

    public void setUserInterface(UserInterface ui) {
        this.userInterface = ui;
    }
    public UserInterface getUserInterface() {
        return this.userInterface;
    }

    /**
     * Line processing using antlr generated lexer and parser.
     *
     */
    public void processLine(String line) {
        if (!line.equals("")) {
            commandHistory.add(line);
            commandIndex = commandHistory.size();
        } else return;

        OSVM_grammarLexer lex = new OSVM_grammarLexer(new ANTLRStringStream(line));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        OSVM_grammarParser g = new OSVM_grammarParser(tokens);
        
        try {
            g.parse();
        } catch (RecognitionException e) {
            e.printStackTrace();
            this.getOut().stdAppend("\nWarning: Mismatched input!");
        }
        if (lex.containsInvalid()) this.getOut().stdAppend("\nWarning: Command contains invalid symbols!");

        // run last command
        String command = g.getCmdTable().get(g.getCmdTable().size() - 1).get(g.getCmdTable().get(0).size() - 1);

        // TODO: more shells
        if (command.equals("kshell")) this.getOut().stdAppend("\nKSHell already running!");
        else if (command.equals("exit")) {
            this.getOut().stdAppend("\nGood bye :-)");
            if (this.getParent() == null) getUserInterface().close();
            else this.getParent().setChild(null);
        }
        command = "" + (char) (command.charAt(0) - 32) + "" + command.substring(1);

        // TODO: replace with create process
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        KSHprocess cmd = null;
        try {
            cmd = (KSHprocess) loader.loadClass("kshos.command." + command).newInstance();
        } catch (Exception ex) {
            this.getOut().stdAppend("\nInvalid command!");
            return;
        }

        // process input
        if (g.getIn() == null) cmd.setIn(userInterface);
        else cmd.setIn(new KSHReader(g.getIn(), this.getWorkingDir()));
        if(!cmd.getIn().stdOpenIn()){
            cmd.processSignal(0);
            this.getOut().stdAppend("\nCannot read " + g.getIn());
            return;
        }
        // process output
        if (g.getOut() == null) cmd.setOut(userInterface);
        else cmd.setOut(new KSHWriter(g.getOut(), this.getWorkingDir()));
        cmd.getOut().stdOpenOut();
        // process parameters
        cmd.setArgs(g.getCmdTable().get(g.getCmdTable().size() - 1));
        this.setChild(cmd);
        cmd.setParent(this);
        // end replace

        cmd.start();
        try {
            cmd.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Shell init.
     * Sets working direcory and command history.
     */
    public void initShell() {
        this.setIn(userInterface);
        this.setOut(userInterface);
        this.setWorkingDir(new File(""));
        commandIndex = -1;
        commandHistory = new ArrayList<String>();
    }

    @Override
    public void run () {
        initShell();
    }

    @Override
    public void processSignal(int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
