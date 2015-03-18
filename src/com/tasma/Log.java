package com.tasma;
//@author A0132763

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This is a singleton service for all the classes to write to a common log file.
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class Log {
	private Level level;
	private FileHandler logFile;
	
	private static Log instance = null;
	
	private Log() throws Exception {
		level = Level.ALL;
		
		logFile = new FileHandler("tasma.log");
		logFile.setFormatter(new SimpleFormatter());
	}
	
	protected static Log getInstance() {
		if (instance == null) {
			try {
				instance = new Log();
			} catch (Exception e) {
				
			}
		}
		return instance;
	}
	
	public static Logger getLogger(String name) {
		Log log = Log.getInstance();
		Logger logger = Logger.getLogger(name);
		
		logger.setLevel(log.level);
		logger.addHandler(log.logFile);
		return logger;
	}
}
