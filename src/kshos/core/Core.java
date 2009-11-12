package kshos.core;

import kshos.ui.*;

/**
 * Class maintains loading of operation system core.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class Core {

    private Login loginWindow = null;

    private UserManager userMgr = null;
    private ProcessManager procMrg = null;
    private UIManager UIMgr = null;

    // <editor-fold defaultstate="collapsed" desc="Singleton implementation.">

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
    public static Core instance() {
        synchronized(Core.class) {
            if (instance == null) {
                instance = new Core();
            }
        }

        return instance;
    }

    // </editor-fold>

    /**
     * Loads all necessary components and starts the OS.
     * Called from constructor.
     */
    private void initialize() {

        // get managers instances
        this.userMgr = UserManager.instance();
        this.procMrg = ProcessManager.instance();
        this.UIMgr = UIManager.instance();

        // TODO: start INIT process

        // start login
        this.loginWindow = new Login();
        
    }

    /**
     * Offers services for other parts of OS.
     *
     * List of services:
     *      0       Halt OS
     *      1       Login
     *      2       Logout
     *
     * List of return flags:
     *      -1      Unspecified error.
     *       0      Success
     *       1      User already loged in.
     *       2      User is not allowed to log in.
     *
     *
     * @param serviceNo
     * @return successfull != -1
     */
    public int service(int serviceNo, Object data) {

        // service no. 1 - Login
        // <editor-fold defaultstate="collapsed">
        if ((serviceNo == 1) && (data instanceof String)) {

            int validationStatus = userMgr.validateAndLoginUser((String) data);

            if (validationStatus == 0) {
                UIMgr.newConsole((String) data);
            }

            return validationStatus;
        }
        // </editor-fold>

        // service no. 0 - Halt OS
        // <editor-fold defaultstate="collapsed">
        if (serviceNo == 0) {
            System.out.println("System is going down!!!");

            // TODO: stop all processes ...
            UIMgr.closeAllConsoles();    // close all consoles
            loginWindow.dispose();                  // close login window
            return 0;
        }
        // </editor-fold>

        // service no. 2 - Logout
        // <editor-fold defaultstate="collapsed">
        if (serviceNo == 2) {

            userMgr.logoutUser((String) data);
            return 0;
        }
        // </editor-fold>

        // in case of error
        System.err.println("Core service error.\nService no.: " + serviceNo +
                "\nService data: " + data);
        return -1;
    }

    /**
     * Tells what does the flag means.
     *
     * @param errorFlag
     * @return error flag meaning
     */
    public String getServiceError(int errorFlag) {

        switch (errorFlag) {
            case -1 : return "";
        }

        // TODO: finish error flag translating
        
        return "Unspecified error.";
    }

}
