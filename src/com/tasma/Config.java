package com.tasma;

import java.util.Properties;

public class Config {
	
	private static Config instance = null;
	
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
