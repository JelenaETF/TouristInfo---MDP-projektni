package application.controller;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import application.MessageBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.infoHotel.HotelServiceInterface;
import services.infoHotel.client.HotelClient;
import services.infoHotel.model.Hotel;
import util.LoggerWrapper;

public class AddNewHotelSceneController implements Initializable {

	@FXML
	private TextField name;
	@FXML
	private TextField address;
	@FXML
	private TextField telephone;
	@FXML
	private ComboBox<String> category;
	
	private static TableView<Hotel> tableToUpdate;
	private LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	private HotelServiceInterface service = HotelClient.getHotelService();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		category.getItems().addAll("1", "2", "3", "4", "5");
	}
	
	@FXML
	public void addHotel() {
		if(name.getText() == null || address.getText() == null || telephone.getText() == null || category.getSelectionModel().getSelectedItem() == null) {
			MessageBox.display("Sva polja moraju biti popunjena!");
		}else {
			try {
				Hotel hotel = new Hotel(name.getText(), address.getText(), telephone.getText(), category.getSelectionModel().getSelectedItem());
				int id = service.add(hotel);
				hotel.setId(id);
				if(!alreadyExistsInTable(hotel)) {
					HotelsSceneController.getHotelsList().add(hotel);
					tableToUpdate.setItems(HotelsSceneController.getHotelsList());
					tableToUpdate.refresh();
					MessageBox.display("Uspjesno dodano!");
				}
			}catch (Exception e) {
				loggerWrapper.getLogger().log(Level.SEVERE, "Problem with communication with server ", e);
			}
		}
	}

	public static TableView<Hotel> getTableToUpdate() {
		return tableToUpdate;
	}

	public static void setTableToUpdate(TableView<Hotel> hotelsTable) {
		AddNewHotelSceneController.tableToUpdate = hotelsTable;
	}
	
	private boolean alreadyExistsInTable(Hotel hotel) {
		for(Hotel h : HotelsSceneController.getHotelsList()) {
			if(h.getId() == hotel.getId())
				return true;
		}
		return false;
	}

}
