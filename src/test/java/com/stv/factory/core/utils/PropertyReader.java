package com.stv.factory.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Properties testProperties = new Properties();

    static {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                testProperties.load(input);
            } else {
                throw new RuntimeException("config.properties file not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    public static String getTestProperty(String key) {
        return testProperties.getProperty(key);
    }
}
