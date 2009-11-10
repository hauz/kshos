/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import kshos.core.KSHprocess;
import kshos.io.KSHReader;

/**
 * CAT command.
 * Reads and prints files specified by parameters.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Cat extends KSHprocess {

    String file = "";

    @Override
    public void run () {
        KSHReader read = null;
        File f = null;
        char[] buff = null;
        int len = getArgs().length;
        
        for (int i = 0; i < len; i++) {
            try {
                f = new File(getArgs()[i]);
                buff = new char[(int)f.length()];
                read = new KSHReader(f);
                read.read(buff, 0, (int)f.length());
                read.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException iex) {
                iex.printStackTrace();
            }
            file += "\n" + new String(buff);
        }
        if (len == 0) {
            // TODO: keyboard input
            file += "\nNo params!";
        }
        ((JTextArea)getOut()).append(file);
    }

}
