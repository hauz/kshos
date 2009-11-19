/**
 * The main entry point of whole application.
 * This class is substitute for boot loader.
 */

package kshos;

import kshos.core.*;

/**
 * Main class. Just runs the program.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 25.10.2009
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Core.instance();    // initiate the core
    }

}
