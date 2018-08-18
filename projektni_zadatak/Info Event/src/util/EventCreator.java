package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Event;
import model.EventCategory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class EventCreator {

	private static List<Event> events = new ArrayList<>();
	private static Gson gson = new Gson();
	
	static{
		LocalDate date1 = LocalDate.of(2018, 8, 12);
		LocalDateTime dateTime1 = date1.atTime(LocalTime.of(21, 00));
		Event first = new Event("Fresh Wave", dateTime1, "Tvrdjava Kastel", "Fresh Wave je najveci festival elektronske muzike u BiH.", EventCategory.FESTIVAL);
		first.setId(1);
		events.add(first);
		
		LocalDate date2 = LocalDate.of(2018, 8, 17);
		LocalDateTime dateTime2 = date2.atTime(LocalTime.of(21, 30));
		Event second = new Event("Kastel Rok Fest", dateTime2, "Tvrdjava Kastel", "Jedan od rok festivala sa najduzom tradicijom odrzava se 13. put na Tvrdjavi Kastel.", EventCategory.FESTIVAL);
		second.setId(2);
		events.add(second);
		
		LocalDate date3 = LocalDate.of(2018, 8, 31);
		LocalDateTime dateTime3 = date3.atTime(LocalTime.of(9, 30));
		Event third = new Event("Koncert etno grupe \"Perezvon\"", dateTime3, "Hram Hrista Spasitelja", "Koncert ce odrzati ruska etno grupa koja nastupa u najprestiznijim dvoranama Moskve.", EventCategory.CONCERT);
		third.setId(3);
		events.add(third);
	}
	
	public static void saveEventsAsJson() {
		String jsonEvents = gson.toJson(events);
		String path = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Event\\resources\\events.json";
		File eventsFile = new File(path);
		if(!eventsFile.exists()) {
			try {
				PrintWriter printWriter = new PrintWriter(new FileWriter(new File(path)));
				printWriter.println(jsonEvents);
				printWriter.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static List<Event> getEventsFromJsonFile(){
		List<Event> events = null;
		String path = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Event\\resources\\events.json";
		File file = new File(path);
		if(!file.exists())
			return null;
	    try {
	    	BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
	    	Type type = new TypeToken<List<Event>>() {}.getType();
	    	events = gson.fromJson(bufferedReader.readLine(), type);
	        bufferedReader.close();
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    return events;
	}
	
	public static void putIntoRadis(List<Event> events) {
		String eventsAsJson = gson.toJson(events);
		JedisPool pool = new JedisPool();
		try(Jedis jedis = pool.getResource()){
			jedis.set("events", eventsAsJson);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("You must start Redis!");
		}
		pool.close();
	}
	
	public static List<Event> getEventsFromRedis(){
		JedisPool pool = new JedisPool();
		List<Event> events = null;
		String eventsAsJson;
		try(Jedis jedis = pool.getResource()){
			eventsAsJson = jedis.get("events");
			Type type = new TypeToken<List<Event>>() {}.getType();
			events = gson.fromJson(eventsAsJson, type);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("You must start Redis!");
		}
		pool.close();
		return events;
	}
	
	public static int getLastUsedId() {
		List<Event> eventsInFile = getEventsFromRedis();
		return Collections.max(eventsInFile).getId();
	}
	
}