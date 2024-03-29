package kshos.core;

import java.util.Properties;
import kshos.core.objects.Process;
import kshos.core.objects.User;
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
    private UIManager UIMgr = null;
    private Properties property = null;

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
        this.UIMgr = UIManager.instance();
        this.property = PropertyManager.loadProperties("kshos/config/kshOS.properties");

        // system configuration file could not be loaded
        if (this.property == null) {
            return;
        }

        // start INIT process
        Process init = ProcessManager.instance().createInitProcess();
        init.start();

        // start login
        this.loginWindow = new Login();
    }

    /**
     * Returns property file with application configuration.
     *
     * @return properties
     */
    public Properties getProperties() {
        return property;
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
                UIMgr.newConsole(userMgr.getUserByName((String) data));
            }

            return validationStatus;
        }
        // </editor-fold>

        // service no. 0 - Halt OS
        // <editor-fold defaultstate="collapsed">
        if (serviceNo == 0) {
            System.out.println("[INFO]: System is going down!!!");

            // close all consoles
            UIMgr.closeAllConsoles();

            // stop all remaining user processes ...
            for (User user: UserManager.instance().getActiveUsers()) {
                ProcessManager.instance().removeAllUserProcesses(user);
            }

            // stop INIT process ...
            Process init = ProcessManager.instance().getProcess(1);
            if (init != null) {

                try {
                    System.out.println("[INFO]: Waiting for INIT process to stop.");
                    init.join();
                    System.out.println("[INFO]: INIT process halted.");
                }
                catch (InterruptedException ex) {
                    System.out.println("[ERR]: Can not terminate INIT process!!!");
                }
                loginWindow.setEnabled(false);
                loginWindow.setVisible(false);

                // remove all references
                loginWindow = null;
                init = null;

                System.gc();

                System.exit(0);
            } else {
                System.out.println("[ERR]: Can not terminate INIT process!!!");
                return -1;
            }

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
        System.err.println("[ERR]: Core service error.\nService no.: " + serviceNo +
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
            case -1 : return getProperties().getProperty("UNSPEC_ERR");
            case 0 : return getProperties().getProperty("LOGIN_OK");
            case 1 : return getProperties().getProperty("ALREADY_IN");
            case 2 : return getProperties().getProperty("LOGIN_NO");
        }

        // TODO: add all flags
        
        return "Unspecified error.";
    }

}
