package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event implements Comparable<Event>{

	private int id;
	private String name;
	private LocalDateTime dateTime;
	private String location;
	private String description;
	private Boolean canceled;
	private EventCategory category;
	
	public Event() {}

	public Event(String name, LocalDateTime dateTime, String location, String description, EventCategory category) {
		super();
		this.name = name;
		this.dateTime = dateTime;
		this.location = location;
		this.description = description;
		this.category = category;
		this.canceled = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name+" "+dateTime+" "+location+" "+description+" "+canceled;
	}

	@Override
	public int compareTo(Event o) {
		return this.getId() - o.getId();
	}
}
