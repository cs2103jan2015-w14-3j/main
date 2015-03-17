package com.tasma;
//@author A0132763

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSetup {
	public static void setup() throws IOException {
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.INFO);
		
		FileHandler logFile = new FileHandler("tasma.log");
		logFile.setFormatter(new SimpleFormatter());
		logger.addHandler(logFile);
	}
}
