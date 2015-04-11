/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This is a singleton service for all the classes to write to a common log file.
 */
public class Log {
	
	private static final String LOG_FILE = "tasma.log";
	
	private Level level;
	private FileHandler logFile;
	
	private static Log instance = null;
	
	private Log() throws Exception {
		level = Level.ALL;
		
		logFile = new FileHandler(LOG_FILE);
		logFile.setFormatter(new SimpleFormatter());
	}
	
	/**
	 * Get the singleton instance of Log
	 * @return Log
	 */
	protected static Log getInstance() {
		if (instance == null) {
			try {
				instance = new Log();
			} catch (Exception e) {
				
			}
		}
		return instance;
	}
	
	/**
	 * Get the logger for the class name
	 * @param name The class name
	 * @return Logger
	 */
	public static Logger getLogger(String name) {
		Log log = Log.getInstance();
		Logger logger = Logger.getLogger(name);
		
		logger.setLevel(log.level);
		logger.addHandler(log.logFile);
		return logger;
	}
}
