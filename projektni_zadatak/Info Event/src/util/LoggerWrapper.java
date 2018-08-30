package util;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import repository.EventRepository;

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
			String path = EventRepository.getProperties().getProperty("logger_path");
			fileHandler = new FileHandler(path, true);
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
