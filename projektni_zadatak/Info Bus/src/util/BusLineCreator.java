package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import services.infoBus.model.BusLine;

public class BusLineCreator {
	
	private static BusLineCreator instance;
	private static Gson gson = new Gson();
	
	public static BusLineCreator getInstance(){
		if(instance == null) {
			instance = new BusLineCreator();
		}
		return instance;
	}
	
	private BusLineCreator(){
	    List<BusLine> lines = new ArrayList<>();
	    
	    HashMap<String, LocalTime> departures1 = new HashMap<>();
	    departures1.put("Celinac", LocalTime.of(18, 25));
	    departures1.put("Zeleni vir", LocalTime.of(18, 30));
	    departures1.put("Vrbanja", LocalTime.of(18, 45));
	    departures1.put("Rebrovac", LocalTime.of(18, 55));
	    departures1.put("Banja Luka", LocalTime.of(19, 00));
	    lines.add(new BusLine("3A", "Celinac", "Banja Luka", departures1));
	    
	    HashMap<String, LocalTime> departures2 = new HashMap<>();
	    departures2.put("Autobuska stanica", LocalTime.of(14, 15));
	    departures2.put("Borik", LocalTime.of(14, 30));
	    departures2.put("Starcevica", LocalTime.of(14, 40));
	    lines.add(new BusLine("14B", "Autobuska stanica", "Starcevica", departures2));
	    
	    HashMap<String, LocalTime> departures3 = new HashMap<>();
	    departures3.put("Autobuska stanica", LocalTime.of(10, 10));
	    departures3.put("Centar", LocalTime.of(10, 20));
	    departures3.put("Paprikovac", LocalTime.of(10, 30));
	    lines.add(new BusLine("20", "Autobuska stanica", "Paprikovac", departures3));
		
	    File file = new File("resources"+File.separator+"bus lines.json");
	    if(!file.exists()) {
	    	try {
	    		PrintWriter printWriter = new PrintWriter(new FileWriter(new File("resources"+File.separator+"bus lines.json")));
	    		printWriter.println(gson.toJson(lines));
	    		printWriter.close();
	    	} catch (Exception e) {
			// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    }
	}
}
