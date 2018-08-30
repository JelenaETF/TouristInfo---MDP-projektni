package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TimeThread extends Thread{
	
	private Label labelToChange;
	private DateTimeFormatter dateTimeFormatter;
	private static volatile boolean turnOff;
	
	public TimeThread(Label labelToChange) {
		this.labelToChange = labelToChange;
		dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		turnOff = false;
	} 
	
	@Override
	public void run() {
		while(!turnOff) {
			LocalDateTime localDateTime = LocalDateTime.now();
			Platform.runLater(() -> {
				labelToChange.setText(localDateTime.format(dateTimeFormatter));
			}
			);
			
			try {
				sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isTurnOff() {
		return turnOff;
	}

	public static void setTurnOff(boolean turnOff) {
		TimeThread.turnOff = turnOff;
	}
}
