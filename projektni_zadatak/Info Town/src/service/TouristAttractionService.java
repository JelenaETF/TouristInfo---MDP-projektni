package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import model.TouristAttraction;

public class TouristAttractionService {

	public static final String PATH = "resources";
	
	private List<TouristAttraction> getAttractionsFromFile(){
		List<TouristAttraction> attractions = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Town\\resources\\tourist_attractions.txt")));
		    String line="";
		    String[] split;
		    while((line = reader.readLine()) != null) {
		    	split = line.split("#");
		    	attractions.add(new TouristAttraction(split[0], split[1], split[2]));
		    }
		    reader.close();
		}catch (Exception e) {
			// TODO: handle exception
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
}