package org.example.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;
    private static final String path = "configuration.properties";

    static {

        try {
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {
        String value = "";
        if (System.getProperty(keyName) == null) {
            value = properties.getProperty(keyName);
        } else {
            value = System.getProperty(keyName);
        }
        return value;
    }

    public static void set(String keyName, String value) throws IOException {
        properties.setProperty(keyName, value);
        FileOutputStream fos = new FileOutputStream(path);
        properties.store(fos, "update values");
    }

}
