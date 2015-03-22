package com.tasma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Config instance = null;
	
	private Properties properties = null;
	private File configFile;
	
	private Config() throws IOException {
		configFile = new File("config.properties");
	    if(!configFile.exists()) {
	    	configFile.createNewFile();
	    }
	    FileInputStream in = new FileInputStream(configFile);
	    properties.load(in);
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
}
