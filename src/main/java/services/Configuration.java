package services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class Configuration {
    public static final String propFile = "./detection-repair.properties";

    public static Properties PROPERTIES = new Properties();

    static {
        try {
            InputStream is = Configuration.class.getResourceAsStream(propFile);
            if (is != null)
                PROPERTIES.load(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
