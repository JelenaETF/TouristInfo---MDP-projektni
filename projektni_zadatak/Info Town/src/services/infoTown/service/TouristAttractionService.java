package services.infoTown.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import services.infoTown.model.TouristAttraction;
import services.infoTown.util.LoggerWrapper;

public class TouristAttractionService {

	private LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	private static final String PROPERTIES_PATH = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Town\\resources\\properties.properties";
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(new FileReader(new File(PROPERTIES_PATH)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<TouristAttraction> getAttractionsFromFile(){
		List<TouristAttraction> attractions = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(properties.getProperty("tourist_attractions_path"))));
		    String line="";
		    String[] split;
		    while((line = reader.readLine()) != null) {
		    	split = line.split("#");
		    	attractions.add(new TouristAttraction(split[0], split[1], split[2]));
		    }
		    reader.close();
		}catch (Exception e) {
			loggerWrapper.getLogger().log(Level.SEVERE, "Problem with loading attractions from file", e);
			return null;
		}
		return attractions;
	}
	
	public TouristAttraction[] getAll() {
		List<TouristAttraction> attractionsAsList = getAttractionsFromFile();
		if(attractionsAsList != null) {
			TouristAttraction[] attractionsAsArray = new TouristAttraction[attractionsAsList.size()];
			for(int i=0; i< attractionsAsList.size(); i++) {
				attractionsAsArray[i] = attractionsAsList.get(i);
			}
			return attractionsAsArray;
		}else return null;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		TouristAttractionService.properties = properties;
	}
	
	
}