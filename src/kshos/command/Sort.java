/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kshos.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;
import kshos.core.KSHprocess;
import kshos.io.KSHReader;

/**
 * SORT command.
 * Sort file or entered lines.
 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
 * @version 0.01 10/11/2009
 */
public class Sort extends KSHprocess {

    

    private String sort(Object[] array) {
        String file = "";
        Arrays.sort(array);
        for (int j = 0; j < array.length; j++) {
            file += "\n" + (String) array[j];
        }
        return file;
    }

    @Override
    public void run () {
        String[] linesA;
        ArrayList<String> lines = new ArrayList<String>();
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
            linesA = (new String(buff)).split("\n");
            for (int j = 0; j < linesA.length; j++) {
                lines.add(linesA[j]);
            }
        }
        if (len == 0) {
            // TODO: keyboard input
            lines.add("");
        }
        ((JTextArea)getOut()).append(sort(lines.toArray()));
    }

}
