package services;

import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class Configuration {
    public static final String propFile = "./config.properties";

    public static Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(Configuration.class.getResourceAsStream(propFile));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
