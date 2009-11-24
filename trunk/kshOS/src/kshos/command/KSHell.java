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
 * @version 0.04 23/11/2009
 */
public class KSHell extends Process {

    private UserInterface userInterface;
    private int commandIndex;
    private ArrayList<String> commandHistory;

    /**
     * Get command history index.
     * Enables work with command history from console (UI).
     * @return index
     */
    public int getCommandIndex() {
        return commandIndex;
    }

    /**
     * Increase command history index.
     * Enables work with command history from console (UI).
     */
    public void incCommandIndex() {
        commandIndex++;
    }

    /**
     * Decrease command history index.
     * Enables work with command history from console (UI).
     */
    public void decCommandIndex() {
        commandIndex--;
    }

    /**
     * Gets full command history.
     * Enables work with command history from console (UI).
     * @return arraylist of strings with command history.
     */
    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Sets console (UI) for shell.
     * @param ui
     */
    public void setUserInterface(UserInterface ui) {
        this.userInterface = ui;
    }

    /**
     * Gets console (UI) for shell.
     * @param ui
     */
    public UserInterface getUserInterface() {
        return this.userInterface;
    }

    /**
     * Line processing using ANTLR generated lexer and parser.
     */
    public void processLine(String line) {
        // parameter test
        if (!line.equals("")) {
            commandHistory.add(line);
            commandIndex = commandHistory.size();
        } else {
            return;
        }

        // grammar init
        OSVM_grammarLexer lex = new OSVM_grammarLexer(new ANTLRStringStream(line));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        OSVM_grammarParser g = new OSVM_grammarParser(tokens);

        // start parsing
        try {
            g.parse();
        } catch (RecognitionException e) {
            e.printStackTrace();
            this.getOut().stdAppend("Warning: Mismatched input!");
        }
        if (lex.containsInvalid()) {            // test for invalid characters
            this.getOut().stdAppend("Warning: Command contains invalid symbols!");
        }

        // command is last command on console line
        String command = g.getCmdTable().get(g.getCmdTable().size() - 1).get(
                g.getCmdTable().get(0).size() - 1);

        // TODO: more shells
        if (command.equals("kshell")) {
            command = "KSHell";
            // uncomment & complete next line when createShell() implemented
            // createShell()
            this.getOut().stdAppend("KSHell already running!");
            return;
        }
        else {
            if (command.equals("exit")) {
                processSignal(0);
            }
        }

        // set command first letter to upper case
        command = "" + (char) (command.charAt(0) - 32) + "" + command.substring(1);

        // create new process and run it
        ProcessManager.instance().createProcess(command,
                userInterface.getUser(), this, userInterface, g);
    }

    /**
     * Shell init.
     * Sets IO, working directory and command history.
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

    /**
     * Signal processing.
     * @param type signal type
     */
    @Override
    public void processSignal(int type) {
        switch (type) {
            case 0:
                this.getOut().stdAppend("Good bye :-)");
                if (this.getParent() == null) getUserInterface().close();
                else this.getParent().removeChild(this.getPID());
                break;
            default:
                break;
        }
    }
}