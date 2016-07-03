package com.belhopat.backoffice.alfresco.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties config;
    private static final String RELATIVE_PATH_TO_PROPERTIES = "config.properties";

    public static Properties getConfig() {
        if (config == null) {
            config = new Properties();
            try {
                   config.load(new FileInputStream(RELATIVE_PATH_TO_PROPERTIES));
               } catch (IOException ioe) {
                   ioe.printStackTrace();
            }

        }
        return config;
    }

}
