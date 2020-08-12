package com.i.lubov.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ColUtils {
    public ColUtils() {
    }

    static String cloToJava(String type) {
        Configuration config = getConfig();

        assert config != null;

        return config.getString(type, "unknowType");
    }

    public static PropertiesConfiguration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException var1) {
            var1.printStackTrace();
            return null;
        }
    }
}
