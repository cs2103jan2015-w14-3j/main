package com.tasma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static final String DESCRIPTION = "";

	private static Config instance = null;
	
	private Properties properties;
	private File configFile;
	
	private Config() throws IOException {
		configFile = new File("config.properties");
		properties = new Properties();
	    if(configFile.exists()) {
		    FileInputStream in = new FileInputStream(configFile);
		    properties.load(in);
		    in.close();
	    } else {
	    	// TODO load all default values
	    }
	}
    
	public static Config getInstance() throws Exception {
		if (instance == null) {
			instance = new Config();
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
	public String setProperty(String key, String value) throws IOException {
		String prevValue = (String) properties.setProperty(key, value);
		saveToFile();
		return prevValue;
	}

	private void saveToFile() throws IOException {
		// TODO Is it inefficient to use a new outputStream every time we save?
		FileOutputStream out = new FileOutputStream("appProperties");
		properties.store(out, DESCRIPTION);
		out.close();
	}
}