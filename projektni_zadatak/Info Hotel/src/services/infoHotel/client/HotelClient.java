package services.infoHotel.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.logging.Level;

import services.infoHotel.HotelServiceInterface;
import util.LoggerWrapper;

public class HotelClient {

	private static LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	public static HotelServiceInterface getHotelService() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File("resources"+File.separator+"serialization.properties")));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String policyPath = properties.getProperty("client_policy_file_path");
		System.setProperty("java.security.policy", policyPath);
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(1099);
			HotelServiceInterface serviceInterface = (HotelServiceInterface) registry.lookup("HotelService");
			return serviceInterface;
		} catch (Exception e) {
			loggerWrapper.getLogger().log(Level.SEVERE, "Problem with finding object in registry", e);
		}
		return null;
	}
}
