package com.belhopat.backoffice.alfresco.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
public class Config {

    private static Properties config;
    
    private static final String RELATIVE_PATH_TO_PROPERTIES = "config.properties";
    
    
    @Value("#{uploadConfiguration['api_key']}")
    private   String API_KEY;

    @Value("#{uploadConfiguration['api_secret']}")
    private   String API_SECRET_KEY;
    
    @Value("#{uploadConfiguration['site']}")
    private   String SITE;
    
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
    
    public String getAPIKey(){
    	return this.API_KEY;
    }

	public String getAPISecretKey() {
		return this.API_SECRET_KEY;
	}

	public String getSite() {
		return this.SITE;
	}

}
