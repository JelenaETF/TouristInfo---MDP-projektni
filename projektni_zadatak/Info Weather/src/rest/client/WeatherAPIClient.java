package rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.logging.Level;

import org.json.JSONObject;

import rest.model.Weather;
import util.LoggerWrapper;

public class WeatherAPIClient {
   
	private static final String API_KEY = "c41eb7d0a1245b98c8929833f6d0155d";
	private static final Integer CITY_ID = 3204541;
	private static final String PATH = "https://api.openweathermap.org/data/2.5/weather?id="+ CITY_ID+"&APPID="+API_KEY;
	private static LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	public static Weather getWeatherInfo() {
		Weather weather = null;;
		try {
		InputStream inputStream = new URL(PATH).openStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
		
		weather = new Weather();
		weather.setTemperature(Double.parseDouble(jsonObject.getJSONObject("main").get("temp").toString()));
		weather.setTemperature(weather.getTemperature() - 273.15);
		weather.setWindSpeed(Double.parseDouble(jsonObject.getJSONObject("wind").get("speed").toString()));
		weather.setGeneralDescription(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
		long sunriseMillis = Long.parseLong(jsonObject.getJSONObject("sys").get("sunrise").toString());
		weather.setSunrise(Instant.ofEpochMilli(sunriseMillis).atZone(ZoneId.systemDefault()).toLocalTime());
		long sunsetMillis = Long.parseLong(jsonObject.getJSONObject("sys").get("sunset").toString());
		weather.setSunset(Instant.ofEpochMilli(sunsetMillis).atZone(ZoneId.systemDefault()).toLocalTime());
		
		return weather;
		}catch (Exception e) {
			loggerWrapper.getLogger().log(Level.SEVERE, "Problem when getting data from Weather API", e);
		}
		return weather;
	}
}
