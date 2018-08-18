package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.infoTown.client.ImageGetter;
import services.infoTown.model.TouristAttraction;

public class AttractionDetailsSceneController implements Initializable {
   
	@FXML
	private TextArea description;
	@FXML
	private ImageView image;
	
	private static String choosenAttractionName;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String choosenDescription="";
		String choosenURL="";
		for(TouristAttraction attraction : TouristAttractionsSceneController.getAttractions()) {
			if(attraction.getName().equals(choosenAttractionName)) {
				choosenDescription = attraction.getDescription();
				choosenURL = attraction.getImageURL();
			}
		}
		description.setWrapText(true);
		description.setText(choosenDescription);
		String name = ImageGetter.saveImageFromURL(choosenURL);
		Image imageTmp = new Image("file:C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Tourist Info\\src\\services\\infoTown\\client\\images\\"+name);
		image.setImage(imageTmp);
	}

	public static String getChoosenAttractionName() {
		return choosenAttractionName;
	}

	public static void setChoosenAttractionName(String choosenAttractionName) {
		AttractionDetailsSceneController.choosenAttractionName = choosenAttractionName;
	}

}
