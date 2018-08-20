package model;

import java.time.LocalTime;

public class TouristInfoClient {

	private String ipAddress;
	private int port;
	private LocalTime activationTime;
	private boolean terminated;
	
	public TouristInfoClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TouristInfoClient(String ipAddress, int port, LocalTime activationTime) {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.activationTime = activationTime;
		this.terminated = false;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public LocalTime getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(LocalTime activationTime) {
		this.activationTime = activationTime;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}
	
}
