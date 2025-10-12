package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties PROPS = new Properties();
    private static final String ENV = System.getProperty("env", "dev");

    static {
        try (InputStream is = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config/" + ENV + ".properties")) {
            if (is == null) {
                throw new RuntimeException("Unable to load properties for env: " + ENV);
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        return sys != null ? sys : PROPS.getProperty(key);
    }

    public static int getInt(String key, int def) {
        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return def;
        }
    }

    public static boolean getBoolean(String key, boolean def) {
        String v = get(key);
        return v == null ? def : Boolean.parseBoolean(v);
    }
}
