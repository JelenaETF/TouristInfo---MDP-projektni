package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import model.Event;
import model.EventCategory;

public class EventClient {

	private static final String BASE_PATH = "http://localhost:8080/InfoEvent/events";
	
	public static List<Event> getAll(){
		List<Event> events = new ArrayList<>();
		try {
			InputStream inputStream = new URL(BASE_PATH).openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String eventsJsonArrayString = bufferedReader.readLine();
			bufferedReader.close();
			
			JSONArray eventsJsonArray = new JSONArray(eventsJsonArrayString);
			JSONObject jsonObject;
			
			for(int i=0; i< eventsJsonArray.length(); i++) {
				jsonObject = eventsJsonArray.getJSONObject(i);
				
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"), dateTimeFormatter);
			    
				Event event = new Event();
				event.setName(jsonObject.getString("name"));
				event.setDateTime(dateTime);
				event.setLocation(jsonObject.getString("location"));
				event.setDescription(jsonObject.getString("description"));
				event.setCanceled(jsonObject.getBoolean("canceled"));
				event.setCategory(EventCategory.valueOf(jsonObject.getString("category")));
				events.add(event);
			}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return events;
	}
	
	public static boolean cancelEvent(Event event) {
		try {
			URL url = new URL(BASE_PATH);
		    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		    httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("PUT");
		    httpURLConnection.setRequestProperty("Content-Type", "application/json");
		    
		    JSONObject jsonObject = new JSONObject(event);
		    OutputStream outputStream = httpURLConnection.getOutputStream();
		    outputStream.write(jsonObject.toString().getBytes());
		    outputStream.flush();
		    
		    if(httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
		    	return false;
		    }
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public static int add(Event event) {
		try {
			URL url = new URL(BASE_PATH);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			
			OutputStream outputStream = httpURLConnection.getOutputStream();
			JSONObject jsonObject = new JSONObject(event);
			outputStream.write(jsonObject.toString().getBytes());
			outputStream.flush();
			
			if(httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return -1;
			}else {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				int id = Integer.parseInt(bufferedReader.readLine());
				bufferedReader.close();
				return id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}
}
