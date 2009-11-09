/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kshos.command;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import javax.swing.JTextArea;
import kshos.command.grammar.*;
import kshos.core.KSHprocess;
import kshos.ui.UserInterface;
import org.antlr.runtime.*;

/**
 * Shell.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.02 8/11/2009
 */
public class KSHell extends KSHprocess {

    private JTextArea console;
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

    public void setConsole(JTextArea con) {
        this.console = con;
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
            console.append("\nWarning: Mismatched input!");
        }
        if (lex.containsInvalid()) console.append("\nWarning: Command contains invalid symbols!");

        // run last command
        String command = g.getCmdTable().get(g.getCmdTable().size() - 1).get(g.getCmdTable().get(0).size() - 1);

        if (command.equals("kshell") && !UserInterface.MORE_SHELLS) console.append("\nKSHell already running!");
        else if (command.equals("exit")) {
            // TODO: kill shell & close console
            console.append("\nGood bye :-)");
        }
        command = "" + (char)(command.charAt(0) - 32) + command.substring(1);

        URLClassLoader loader = new URLClassLoader(new URL[0]);
        KSHprocess cmd = null;
        try {
            cmd = (KSHprocess) loader.loadClass("kshos.command." + command).newInstance();
        } catch (Exception ex) {
            console.append("\nInvalid command!");
            return;
        }
        // TODO: file in&out
        // process input
        if (g.getIn() != null) cmd.setIn(g.getIn());
        if (g.getOut() == null) cmd.setOut(console);
        else cmd.setOut(g.getOut());
        // process parameters
        cmd.setArgs(g.getCmdTable().get(0));
        cmd.setParent(this);
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
        setWorkingDir(new File(""));
        commandIndex = -1;
        commandHistory = new ArrayList<String>();
    }

    @Override
    public void run () {
        initShell();
    }
}
