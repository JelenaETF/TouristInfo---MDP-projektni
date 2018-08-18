package application.controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import services.infoBus.model.BusLine;

public class BusLineDetailsSceneController implements Initializable{
	
	private static BusLine lineToShowDetails;
	
	@FXML
	private TextArea detailsTextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			String toShow="";
			HashMap<String, LocalTime> details = BusLinesSceneController.getBusLineClient().getDetailsForLine(lineToShowDetails.getLineNum());
			for(String station : details.keySet()) {
				toShow += station+" - "+details.get(station);
				toShow += System.lineSeparator();
			}
			toShow += System.lineSeparator();
			toShow += BusLinesSceneController.getBusLineClient().getStatusForLine(lineToShowDetails.getLineNum());
			detailsTextArea.setText(toShow);
			detailsTextArea.setEditable(false);
			detailsTextArea.setWrapText(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setLineToShowDetails(BusLine selectedItem) {
	  BusLineDetailsSceneController.lineToShowDetails = selectedItem;	
	}

	public static BusLine getLineToShowDetails() {
		return lineToShowDetails;
	}
}
