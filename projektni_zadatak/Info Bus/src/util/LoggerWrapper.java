package util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerWrapper {

	private Logger logger;
	private static LoggerWrapper instance;
	private FileHandler fileHandler;
	private SimpleFormatter simpleFormatter;
	
	public static LoggerWrapper getInstance() {
		if(instance == null) {
			instance = new LoggerWrapper();
		}
		return instance;
	}
	
	private LoggerWrapper() {
		try {
			logger = Logger.getLogger(LoggerWrapper.class.getName());
			fileHandler = new FileHandler("resources"+File.separator+"LoggerFile.log", true);
			simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
