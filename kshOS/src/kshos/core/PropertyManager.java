package kshos.core;

import java.util.Properties;

/**
 * Class maintains applications properties.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 13.11.2009
 */
public class PropertyManager {

    // <editor-fold desc="Singleton implementation" defaultstate="collapsed">

    private static PropertyManager instance = null;

    /**
     * Initial constructor.
     */
    protected PropertyManager() {
        // blank constructor.
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
    public static PropertyManager instance() {

        synchronized (PropertyManager.class) {
            if (instance == null) {
                instance = new PropertyManager();
            }
        }

        return instance;
    }
    
    // </editor-fold>

    /**
     * Loads property file.
     *
     * @param fileName
     * @return property
     */
    public static Properties loadProperties(String fileName) {

        Properties prop = new Properties();

        try {
            prop.load(ClassLoader.getSystemResourceAsStream(fileName));
        }
        catch (Exception ex) {
            System.err.println("Error loading property file: " + fileName);
            prop = null;
        }

        return prop;
    }

}
