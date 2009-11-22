/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kshos.command;

import java.io.File;
import java.util.ArrayList;
import kshos.command.grammar.*;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;
import kshos.ui.UserInterface;
import org.antlr.runtime.*;

/**
 * Shell.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.03 15/11/2009
 */
public class KSHell extends Process {

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
        // parameter test
        if (!line.equals("")) {
            commandHistory.add(line);
            commandIndex = commandHistory.size();
        } else {
            return;
        }

        OSVM_grammarLexer lex = new OSVM_grammarLexer(new ANTLRStringStream(line));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        OSVM_grammarParser g = new OSVM_grammarParser(tokens);

        // start parsing
        try {
            g.parse();
        } catch (RecognitionException e) {
            e.printStackTrace();
            this.getOut().stdAppend("\nWarning: Mismatched input!");
        }
        if (lex.containsInvalid()) {            // test for invalid characters
            this.getOut().stdAppend("\nWarning: Command contains invalid symbols!");
        }

        // run last command
        String command = g.getCmdTable().get(g.getCmdTable().size() - 1).get(
                g.getCmdTable().get(0).size() - 1);

        // TODO: more shells
        if (command.equals("kshell")) {
            this.getOut().stdAppend("\nKSHell already running!");
        }
        else {
            if (command.equals("exit")) {
                this.getOut().stdAppend("\nGood bye :-)");
                if (this.getParent() == null) {
                    getUserInterface().close();
                }
            }
            else {
                // FIXME: ???
//                this.getParent().setChild(null);
            }
        }
        
        command = "" + (char) (command.charAt(0) - 32) + "" + command.substring(1);

        // create new process and run it
        ProcessManager.instance().createProcess(command, null, this, userInterface, g);
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
    public void tick () {
        initShell();
    }

    @Override
    public void processSignal(int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
