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
    private Login loginWindow = null;

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
    public static Core instance() {
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
        loginWindow = new Login();
        
    }

    /**
     * Offers services for other parts of OS.
     *
     * List of services:
     *      0       Halt OS
     *      1       Login
     * 
     *
     * @param serviceNo
     * @return successfull [true/false]
     */
    public boolean service(int serviceNo, Object data) {
        if ((serviceNo == 1) && (data instanceof String)) {
            System.out.print("Successfull login...");
            UIManager.instance().newConsole((String) data);
        }

        if (serviceNo == 0) {
            // TODO: stop all consoles and all threads ...
            System.out.println("System is going down!!!");
            UIManager.instance().closeAllConsoles();
            loginWindow.dispose();
        }

        return false;
    }

}
