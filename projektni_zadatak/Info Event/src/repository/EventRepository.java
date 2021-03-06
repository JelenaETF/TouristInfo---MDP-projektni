package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import model.Event;
import model.EventCategory;
import util.EventCreator;

public class EventRepository {
   
	private static final String PROPERTIES_PATH = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Event\\resources\\properties.properties";
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
	
	public EventRepository(){
		File file = new File(properties.getProperty("events_file_path"));
		if(!file.exists()) {
			EventCreator.saveEventsAsJson();
			EventCreator.putIntoRadis(EventCreator.getEventsFromJsonFile());
		}
		events = EventCreator.getEventsFromRedis();
	}
	
	private List<Event> events;
	
	public List<Event> getAll(){
		return events.stream().filter(e -> e.getCanceled() == false && !eventPassed(e)).collect(Collectors.toList());
	}
	
	public List<Event> getEventsByCategory(EventCategory category) {
		if(events == null) 	return null;
		return events.stream().filter(e -> e.getCategory().equals(category) && !eventPassed(e) && e.getCanceled() == false).collect(Collectors.toList());
	}
	
	public List<Event> getEventsByDate(LocalDate date){
		if(events == null) return null;
		return events.stream().filter(e -> e.getDateTime().toLocalDate().isEqual(date) && e.getCanceled() == false).collect(Collectors.toList());
	}
	
	public List<Event> getEventsBetweenDates(LocalDate from, LocalDate to){
		if(events == null) return null;
		return events.stream().filter(e -> (e.getDateTime().toLocalDate().isEqual(from) || (e.getDateTime().toLocalDate().isAfter(from) && e.getDateTime().toLocalDate().isBefore(to))) && e.getCanceled() == false ).collect(Collectors.toList());
	}
	
	public List<Event> getEventsByLocation(String location){
		if(events == null) return null;
		return events.stream().filter(e -> e.getLocation().equals(location) && !eventPassed(e) && e.getCanceled() == false).collect(Collectors.toList());
	}
	
	private boolean eventPassed(Event event) {
		return event.getDateTime().isBefore(LocalDateTime.now());
	}
	
	public int add(Event event) {
		if(event == null) return -1;
		if(events == null) return 0;
		int lastUsedId = EventCreator.getLastUsedId();
		lastUsedId++;
		event.setId(lastUsedId);
		events.add(event);
		EventCreator.putIntoRadis(events);
		return lastUsedId;
	}
	
	public boolean cancel(int id) {
		for(Event event : events) {
			if(event.getId() == id) {
				event.setCanceled(true);
				EventCreator.putIntoRadis(events);
				return true;
			}
		}
		return false;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		EventRepository.properties = properties;
	}
	
	
}
