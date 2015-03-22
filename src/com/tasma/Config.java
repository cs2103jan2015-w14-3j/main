package com.tasma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Config instance = null;
	
	private Properties properties;
	private File configFile;
	
	private Config() throws IOException {
		configFile = new File("config.properties");
	    if(!configFile.exists()) {
	    	configFile.createNewFile();
	    }
	    FileInputStream in = new FileInputStream(configFile);
	    properties.load(in);
	    in.close();
	}
    
	protected static Config getInstance() {
		if (instance == null) {
			try {
				instance = new Config();
			} catch (Exception e) {

			}
		}
		return instance;
	}
	
	/**
	 * Searches for the property with the specified key in this property list.
	 * Returns null if the property is not found.
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * Sets the property based on specified key and value.
	 * @return the previous value of the specified key in this property list, 
	 *         or null if it did not have one
	 */
	public String setProperty(String key, String value) {
		return (String) properties.setProperty(key, value);
	}
}
