package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainSceneController {

	@FXML
	private BorderPane borderPane;
	
	@FXML
	public void showTouristAttractions() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/TouristAttractionsScene.fxml"));
			borderPane.setCenter(root);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showHotels() {
	  try {
		  Parent root = FXMLLoader.load(getClass().getResource("/application/view/HotelsScene.fxml"));
		  borderPane.setCenter(root);
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
	
	@FXML
	public void showBusLines() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/BusLinesScene.fxml"));
			borderPane.setCenter(root);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showEvents() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/EventsScene.fxml"));
			borderPane.setCenter(root);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showWeather() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/WeatherScene.fxml"));
			borderPane.setCenter(root);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showTime() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/TimeScene.fxml"));
			borderPane.setCenter(root);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
