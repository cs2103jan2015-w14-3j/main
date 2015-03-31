/**
 * Tasma Task Manager
 */
//@author 
package com.tasma.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.tasma.config.observers.StorageObserver;

public class Config extends ObservableConfig {
	
	private static final String DESCRIPTION = "";
	private static final String CONFIG_DEFAULT_FILENAME = "app";
	private static final String CONFIG_FILE_EXTENSION = ".config";
	private static final HashMap<String, Config> instances = new HashMap<String, Config>();
	
	private boolean isFirstRun = false;
	private Properties properties;
	private File configFile;
	
	private Config(String name) throws IOException {
		configFile = new File(name + CONFIG_FILE_EXTENSION);
		properties = new Properties();
		loadDefaultsAndObservers();
	    if(configFile.exists()) {
		    FileInputStream in = new FileInputStream(configFile);
		    properties.load(in);
		    in.close();
	    } else {
	    	isFirstRun = true;
	    }
	}

	public static Config getInstance() throws Exception {
		return getInstance(CONFIG_DEFAULT_FILENAME);
	}
	
	public static Config getInstance(String name) throws Exception {
		if (!instances.containsKey(name)) {
			instances.put(name, new Config(name));
		}
		return instances.get(name);
	}
	
	private void loadDefaultsAndObservers() {
		StorageObserver storage = new StorageObserver();
		for (Map.Entry<String, String> entry : storage.defaults().entrySet())
		{
			properties.setProperty(entry.getKey(), entry.getValue());
		}
		
		this.addObserver(storage);
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
		String prevValue = properties.getProperty(key);
		if (prevValue != null && prevValue.equals(value)) {
			
		} else {
			properties.setProperty(key, value);
			notifyObservers(key, prevValue, value);
			saveToFile();
		}
		return prevValue;
	}
	
	public boolean isFirstRun()
	{
		return isFirstRun;
	}

	private void saveToFile() throws IOException {
		// TODO Is it inefficient to use a new outputStream every time we save?
		FileOutputStream out = new FileOutputStream(configFile);
		properties.store(out, DESCRIPTION);
		out.close();
	}
}
