package application.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import application.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.infoHotel.HotelServiceInterface;
import services.infoHotel.client.HotelClient;
import services.infoHotel.model.Hotel;
import util.LoggerWrapper;

public class HotelsSceneController implements Initializable{

	@FXML
	TableView<Hotel> hotelsTable;
	@FXML
	TableColumn<String, Hotel> name;
	@FXML
	TableColumn<String, Hotel> address;
	@FXML
	TableColumn<String, Hotel> telephone;
	@FXML
	TableColumn<String, Hotel> category;
	
	private static ObservableList<Hotel> hotelsList = FXCollections.observableArrayList();
	private HotelServiceInterface service = HotelClient.getHotelService();
	private LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	  generateTable();	
	}
	
	private void generateTable() {
	  name.setCellValueFactory(new PropertyValueFactory<>("name"));
	  address.setCellValueFactory(new PropertyValueFactory<>("address"));
	  telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
	  category.setCellValueFactory(new PropertyValueFactory<>("category"));
	 
	  try {
		hotelsList.addAll(service.getAll());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		loggerWrapper.getLogger().log(Level.SEVERE, "Problem when try to get hotels", e);
	}
	  hotelsTable.setItems(hotelsList);
	  AddNewHotelSceneController.setTableToUpdate(hotelsTable);
	}

	@FXML
	public void openStageForAdding() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/AddNewHotelScene.fxml"));
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
	
	@FXML
	public void delete() {
		if(hotelsTable.getSelectionModel().getSelectedItem() == null) {
			MessageBox.display("Odaberite hotel koji zelite obrisati!");
		}else {
			Hotel toDelete = hotelsTable.getSelectionModel().getSelectedItem();
			try {
				if(service.delete(toDelete.getId())){
					hotelsList.remove(toDelete);
					hotelsTable.setItems(hotelsList);
					hotelsTable.refresh();
					MessageBox.display("Uspjesno uklonjeno!");
				}else
					MessageBox.display("Greska prilikom uklanjanja!");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				loggerWrapper.getLogger().log(Level.SEVERE, "Problem when trying to remove hotel", e);
			}
		}
	}
	
	public static ObservableList<Hotel> getHotelsList() {
		return hotelsList;
	}

	public static void setHotelsList(ObservableList<Hotel> hotelsList) {
		HotelsSceneController.hotelsList = hotelsList;
	}
}
