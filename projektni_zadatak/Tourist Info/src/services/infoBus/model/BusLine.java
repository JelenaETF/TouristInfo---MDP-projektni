package services.infoBus.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;

public class BusLine implements Serializable{

	private String lineNum;
	private String from;
	private String to;
	private HashMap<String, LocalTime> departures;
	
	public BusLine() {}

	public BusLine(String lineNum, String from, String to, HashMap<String, LocalTime> departures) {
		super();
		this.lineNum = lineNum;
		this.from = from;
		this.to = to;
		this.departures = departures;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public HashMap<String, LocalTime> getDepartures() {
		return departures;
	}

	public void setDepartures(HashMap<String, LocalTime> departures) {
		this.departures = departures;
	}
	
}
