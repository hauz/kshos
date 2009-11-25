package kshos.command;

import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.Process;

/**
 * MAN command.
 * Prints help and implemented commands.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Man extends Process {

    /**
     * Help text.
     */
    private static final String MAN = "MAN page for kshOS Virtual Machine Manager\n" +
            "Usage: command [ params ] [ < file_input ] [ | command params ] [ > file_output ]" +
            "\nImplemanted commands:\n" +
            "man - this help\n" +
            "cd param - change directory to param\n" +
            "pwd - prints current directory\n" +
            "ls [param] - list current/param folder\n" +
            "echo [params] - prints params\n" +
            "cat [params] - reads params specified files, merge this files";

    /**
     * Process main function.
     */
    @Override
    public void tick () {
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("MAN_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
        } else this.getOut().stdAppend(MAN);

        this.getOut().stdCloseOut();
        this.getParent().removeChild(this.getPID());
        ProcessManager.instance().removeProcess(this.getPID());
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdAppend("Cannot process line!");
        processSignal(0);
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
                ProcessManager.instance().removeProcess(this.getPID());
                break;
            default:
                break;
        }
    }

}
