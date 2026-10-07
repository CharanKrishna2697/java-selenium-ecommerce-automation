package com.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {

        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "config.properties file was not found"
                );
            }

            properties.load(input);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load configuration file",
                    e
            );
        }
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null) {

            throw new RuntimeException(
                    "Configuration property not found: " + key
            );
        }

        return value;
    }
}