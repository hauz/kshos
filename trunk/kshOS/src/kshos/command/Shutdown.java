/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

    @Override
    public void tick () {
        System.exit(0);     // disgusting thing ...
        //         Core.instance().service(0, null);
    }

    @Override
    public void processLine(String line) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processSignal(int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
