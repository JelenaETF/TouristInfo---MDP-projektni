package serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import services.infoHotel.model.Hotel;

public class GsonSerialization {

	private static Gson gson = new Gson();
	
    public static void serializeHotel(Hotel hotel) throws Exception {
    	String path = "resources"+File.separator+"serialized"+File.separator+"gson"+File.separator+ hotel.getId()+".json";
    	PrintWriter writer = new PrintWriter(new FileWriter(new File(path)));
    	String jsonObject = gson.toJson(hotel);
    	writer.write(jsonObject);
    	writer.close();
    }
	
    public static Hotel deserializeHotel(int id) {
    	Hotel hotel = null;
    	try {
    		String path = "resources"+File.separator+"serialized"+File.separator+"gson"+File.separator+ id +".json";
    		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
    		hotel = gson.fromJson(reader, Hotel.class);
    		reader.close();
    		return hotel;
    	}catch (Exception e) {
			// TODO: handle exception
    		return null;
		}
    }
    
    public static List<Hotel> deserializeAll(){
    	try {
    		String path = "resources"+File.separator+"serialized"+File.separator+"gson";
    		List<Hotel> gsonHotels = new ArrayList<>();
    		File gsonDir = new File(path);
    	
    		BufferedReader reader = null;
    		for(File file : gsonDir.listFiles()) {
    			reader = new BufferedReader(new FileReader(file));
    			gsonHotels.add(gson.fromJson(reader, Hotel.class));	
    			reader.close();
    		}
  
    		return gsonHotels;
    	}catch (Exception e) {
			// TODO: handle exception
    		return null;
		}
    }
	
}
