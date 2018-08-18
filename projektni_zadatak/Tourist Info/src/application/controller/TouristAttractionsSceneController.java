package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.infoTown.client.InfoTownClient;
import services.infoTown.model.TouristAttraction;

public class TouristAttractionsSceneController implements Initializable {

	@FXML
	private ListView<String> names;
	private static List<TouristAttraction> attractions = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	   attractions = InfoTownClient.getTouristAttractions();
	   for(TouristAttraction attraction : attractions) {
	      names.getItems().add(attraction.getName());
	   }
	}
	
	@FXML
	public void openSceneForDetails() {
		if(names.getSelectionModel().getSelectedItem() != null) {
			try {
				AttractionDetailsSceneController.setChoosenAttractionName(names.getSelectionModel().getSelectedItem());
				Parent root = FXMLLoader.load(getClass().getResource("/application/view/AttractionDetailsScene.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public static List<TouristAttraction> getAttractions() {
		return attractions;
	}

}
