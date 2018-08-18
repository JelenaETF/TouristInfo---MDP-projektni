package services.infoEvent.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import services.infoEvent.model.*;

public class EventClient {
    
	private static final String BASE_PATH = "http://localhost:8080/InfoEvent/events";
	
	public static List<Event> getAllByCategory(EventCategory category) {
		List<Event> eventsByCategory = new ArrayList<>();
		try {
		InputStream inputStream = new URL(BASE_PATH+"/category/"+category.toString()).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
		
		JSONObject jsonObject = null;
		for(int i=0; i<jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"), dateTimeFormatter);
			
			Event event = new Event();
			event.setName(jsonObject.getString("name"));
			event.setCanceled(jsonObject.getBoolean("canceled"));
			event.setCategory(EventCategory.valueOf(jsonObject.getString("category")));
			event.setLocation(jsonObject.getString("location"));
			event.setDescription(jsonObject.getString("description"));
			event.setId(jsonObject.getInt("id"));
			event.setDateTime(dateTime);
			eventsByCategory.add(event);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return eventsByCategory;
	}
	
	public static List<String> getAllLocations() {
		List<String> allLocations = new ArrayList<>();
		try {
		InputStream inputStream = new URL(BASE_PATH).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
		
		JSONObject jsonObject = null;
		for(int i=0; i<jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			allLocations.add(jsonObject.getString("location"));
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return allLocations;
	}
	
	public static List<Event> getAllByLocation(String location) {
		List<Event> eventsByLocation = new ArrayList<>();
		try {
		String encodedLocation = URLEncoder.encode(location, "UTF-8");
		InputStream inputStream = new URL(BASE_PATH+"/location/"+encodedLocation).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
		
		JSONObject jsonObject = null;
		for(int i=0; i<jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"), dateTimeFormatter);
			
			Event event = new Event();
			event.setName(jsonObject.getString("name"));
			event.setCanceled(jsonObject.getBoolean("canceled"));
			event.setCategory(EventCategory.valueOf(jsonObject.getString("category")));
			event.setLocation(jsonObject.getString("location"));
			event.setDescription(jsonObject.getString("description"));
			event.setId(jsonObject.getInt("id"));
			event.setDateTime(dateTime);
			eventsByLocation.add(event);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return eventsByLocation;
	}
	
	public static List<Event> getAllByDate(String date) {
		List<Event> eventsByDate = new ArrayList<>();
		try {
		InputStream inputStream = new URL(BASE_PATH+"/date/"+date).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
		
		JSONObject jsonObject = null;
		for(int i=0; i<jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"), dateTimeFormatter);
			
			Event event = new Event();
			event.setName(jsonObject.getString("name"));
			event.setCanceled(jsonObject.getBoolean("canceled"));
			event.setCategory(EventCategory.valueOf(jsonObject.getString("category")));
			event.setLocation(jsonObject.getString("location"));
			event.setDescription(jsonObject.getString("description"));
			event.setId(jsonObject.getInt("id"));
			event.setDateTime(dateTime);
			eventsByDate.add(event);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return eventsByDate;
	}
	
	public static List<Event> getAllByPeriod(String dateFrom, String dateTo) {
		List<Event> eventsByPeriod = new ArrayList<>();
		try {
		InputStream inputStream = new URL(BASE_PATH+"/period/"+dateFrom+"/"+dateTo).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
		
		JSONObject jsonObject = null;
		for(int i=0; i<jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"), dateTimeFormatter);
			
			Event event = new Event();
			event.setName(jsonObject.getString("name"));
			event.setCanceled(jsonObject.getBoolean("canceled"));
			event.setCategory(EventCategory.valueOf(jsonObject.getString("category")));
			event.setLocation(jsonObject.getString("location"));
			event.setDescription(jsonObject.getString("description"));
			event.setId(jsonObject.getInt("id"));
			event.setDateTime(dateTime);
			eventsByPeriod.add(event);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return eventsByPeriod;
	}
}
