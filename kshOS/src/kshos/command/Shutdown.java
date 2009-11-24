package kshos.command;

import kshos.core.Core;
import kshos.core.objects.Process;

/**
 * SHUTDOWN command.
 * Exits VMM.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 19/11/2009
 */
public class Shutdown extends Process {

    /**
     * Process main function.
     */
    @Override
    public void tick () {
        if (getArgs().length > 0 && getArgs()[0].charAt(0) == '-') {
            if (getArgs()[0].charAt(1) == 'h') {
                this.getOut().stdWriteln(Core.instance().getProperties().getProperty("SHUTDOWN_HLP"));
            } else {
                this.getErr().stdWriteln("Bad parameter!");
            }
            this.getParent().removeChild(this.getPID());
            return;
        }
        // FIXME: fakt nevim :)
        Process p = this;
        // ends all running processes up tu shell
        while (p.getParent() != null) {
            p.processSignal(0);
            p = p.getParent();
        }
        //p.processSignal(0);
        //Core.instance().service(0, null);
    }

    /**
     * Line processing.
     * Doesnt have console input.
     * @param line inputed line
     */
    @Override
    public void processLine(String line) {
        this.getErr().stdAppend("Cannot process line!");
        this.getParent().removeChild(this.getPID());
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
