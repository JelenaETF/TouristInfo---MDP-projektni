package application.controller;

import java.net.MulticastSocket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import services.infoWeather.client.MulticastClient;

public class WeatherSceneController implements Initializable{

	@FXML
	private TextArea weatherTextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		weatherTextArea.setWrapText(true);
		weatherTextArea.setText(MulticastClient.getWeatherInfo());
	}

}
