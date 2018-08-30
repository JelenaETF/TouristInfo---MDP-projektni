package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import services.infoBus.model.BusLine;

public class BusLineReader {

	private static Gson gson = new Gson();
	private static LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	public static List<BusLine> getAll(){
		List<BusLine> lines = null;;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("resources"+File.separator+"bus lines.json")));
			Type type = new TypeToken<List<BusLine>>() {}.getType();
			lines = gson.fromJson(reader.readLine(), type);
			reader.close();
		}catch (Exception e) {
			loggerWrapper.getLogger().log(Level.SEVERE, "Problem with loading bus line data from file", e);
		}
		return lines;
	} 
}
