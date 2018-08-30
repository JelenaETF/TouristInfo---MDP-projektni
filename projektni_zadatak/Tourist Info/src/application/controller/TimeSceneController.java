package application.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.TimeThread;


public class TimeSceneController implements Initializable{

	@FXML
	private Label timeLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TimeThread timeThread = new TimeThread(timeLabel);
		timeThread.start();
	}
}
