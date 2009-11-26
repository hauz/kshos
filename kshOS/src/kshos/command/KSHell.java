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
        // line test
        if ((line.contains("kshell") && !line.equals("kshell")) || (line.contains("exit") && !line.equals("exit"))) {
            this.getErr().stdWriteln("Error: kshell and exit can not be piped!");
            return;
        }
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
            this.getErr().stdWriteln("Warning: Mismatched input!");
        }
        if (lex.containsInvalid()) {            // test for invalid characters
            this.getErr().stdWriteln("Warning: Command contains invalid symbols!");
        }

        int pr, pa;
        pr = g.getCmdTable().size() - 1;
        pa = g.getCmdTable().get(pr).size() - 1;
        // command is last command on console line
        String command = g.getCmdTable().get(pr).get(pa);

        if (command.equals("kshell")) {
            ProcessManager.instance().createShell(getUserInterface(), this.getPID());
            return;
        } else if (command.equals("exit")) {
                processSignal(0);
                return;
        }
        // create new process(es) and run it
        ProcessManager.instance().createProcess(this, userInterface, g);
    }

    /**
     * Shell init.
     * Sets IO, working directory and command history.
     */
    public void initShell() {
        this.setIn(userInterface);
        this.setOut(userInterface);
        this.setErr(userInterface);
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
                this.getOut().stdWriteln("Good bye :-)");
                // if parent is init then close
                if (this.getParent().getPID() == 1 &&
                        ProcessManager.instance().getLastShell(getUserInterface().getUser()).equals(this))
                        getUserInterface().close();
                // else exit last shell
                else {
                    // put all children to new parent
                    while (this.getAllChilds().size() > 0) {
                        this.getChild(this.getAllChilds().firstKey()).setParent(this.getParent());
                        this.getParent().addChild(this.getChild(this.getAllChilds().firstKey()));
                        this.removeChild(this.getAllChilds().firstKey());
                    }
                    this.getIn().stdCloseIn();
                    this.getOut().stdCloseOut();
                    this.getParent().removeChild(this.getPID());
                    ProcessManager.instance().removeProcess(this.getPID());
                }
                break;
            default:
                break;
        }
    }
}
