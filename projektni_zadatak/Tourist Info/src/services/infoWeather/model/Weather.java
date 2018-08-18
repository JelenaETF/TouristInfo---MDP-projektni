package services.infoWeather.model;

import java.time.LocalTime;

import javafx.scene.shape.Line;

public class Weather {

	private double temperature;
	private String generalDescription;
	private double windSpeed;
	private LocalTime sunrise;
	private LocalTime sunset;
	
	public Weather() {}
	
	public Weather(double temperature, String generalDescription, double windSpeed, LocalTime sunrise, LocalTime sunset) {
		super();
		this.temperature = temperature;
		this.generalDescription = generalDescription;
		this.windSpeed = windSpeed;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getGeneralDescription() {
		return generalDescription;
	}
	public void setGeneralDescription(String generalDescription) {
		this.generalDescription = generalDescription;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public LocalTime getSunrise() {
		return sunrise;
	}
	public void setSunrise(LocalTime sunrise) {
		this.sunrise = sunrise;
	}
	public LocalTime getSunset() {
		return sunset;
	}
	public void setSunset(LocalTime sunset) {
		this.sunset = sunset;
	}
	
	@Override
	public String toString() {
		return "Temperatura: "+temperature + System.lineSeparator() + "Opsti opis: "+generalDescription+System.lineSeparator()+
				"Brzina vjetra: "+windSpeed+System.lineSeparator()+"Izlazak sunca: "+sunrise+System.lineSeparator()+
				"Zalazak sunca: "+sunset+System.lineSeparator();
	}
	
}
