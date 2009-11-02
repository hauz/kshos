package kshos.core;

/**
 * Process management class.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 1.11.2009
 */
public class ProcessManager {

    private static ProcessManager instance = null;

    /**
     * Initial constructor.
     */
    protected ProcessManager() {
        // blank constructor
    }

    /**
     * Destroy actual instance.
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Actual class instance.
     *
     * @return instance
     */
    public ProcessManager instance() {

        synchronized(ProcessManager.class) {
            if (instance == null) {
                instance = new ProcessManager();
            }
        }

        return instance;
    }


}
