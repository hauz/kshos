package kshos.core;

import kshos.ui.*;

/**
 * Class maintains loading of operation system core.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class Initializer {

    /**
     * Main constructor.
     */
    public Initializer() {
        // blank constructor
    }

    /**
     * Loads all necessary components and starts the OS.
     */
    public void initialize() {
        UIManager.getInstance().newConsole();
        // TODO: add other managers
        
    }

}
