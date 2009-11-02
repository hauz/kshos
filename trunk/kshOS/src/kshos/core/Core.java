package kshos.core;

import kshos.ui.*;

/**
 * Class maintains loading of operation system core.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class Core {

    /**
     * Main constructor.
     */
    public Core() {
        // blank constructor
    }

    /**
     * Loads all necessary components and starts the OS.
     */
    public void initialize() {
        new Login();
        UIManager.getInstance().newConsole();
        // TODO: add other managers
        
    }

    /**
     * Offers services for other parts of OS.
     *
     * @param serviceNo
     * @return successfull [true/false]
     */
    public boolean service(int serviceNo) {
        return false;
    }

}
