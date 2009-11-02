package kshos.core;

import kshos.ui.*;

/**
 * Class maintains loading of operation system core.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class Core {

    private static Core instance = null;

    /**
     * Main constructor.
     */
    protected Core() {
        initialize();
    }

    /**
     * Destroy actual instnace.
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Return actual class instance.
     *
     * @return actual instance
     */
    public static Core instance () {
        synchronized(Core.class) {
            if (instance == null) {
                instance = new Core();
            }
        }

        return instance;
    }

    /**
     * Loads all necessary components and starts the OS.
     */
    private void initialize() {
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
        if (serviceNo == 1)
            System.out.print("Doslo k loginu!!!!");

        return false;
    }

}
