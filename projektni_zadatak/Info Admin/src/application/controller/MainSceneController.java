package application.controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TouristInfoClient;
import server.ConnectionTestThread;
import server.ServerMainThread;
import server.ServerThread;

public class MainSceneController implements Initializable{
   
	@FXML
	private TableView<TouristInfoClient> clientsTable;
	@FXML
	private TableColumn<String, TouristInfoClient> ipAddress;
	@FXML
	private TableColumn<Integer, TouristInfoClient> port;
	@FXML
	private TableColumn<LocalTime, TouristInfoClient> activationTime;
	
	private static ObservableList<TouristInfoClient> clientInfoList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generateTable();
		ServerThread.setTableToUpdate(clientsTable);
		ConnectionTestThread.setTableToUpdate(clientsTable);
	}
	
	private void generateTable() {
		ipAddress.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
		port.setCellValueFactory(new PropertyValueFactory<>("port"));
		activationTime.setCellValueFactory(new PropertyValueFactory<>("activationTime"));
	}

	public static ObservableList<TouristInfoClient> getClientInfoList() {
		return clientInfoList;
	}

	public static void setClientInfoList(ObservableList<TouristInfoClient> clientInfoList) {
		MainSceneController.clientInfoList = clientInfoList;
	}
	
	@FXML
	public void shutDown() {
		if(clientsTable.getSelectionModel().getSelectedItem() == null) {
			MessageBox.display("Morate odabrati aplikaciju za gasenje!");
		}else {
			TouristInfoClient clientFromTable = clientsTable.getSelectionModel().getSelectedItem();
			for(TouristInfoClient clientInfo : clientInfoList) {
				if(clientInfo.getIpAddress().equals(clientFromTable.getIpAddress()) && clientInfo.getPort() == clientFromTable.getPort()) {
					clientInfo.setTerminated(true);
				}
			}
			clientInfoList.remove(clientFromTable);
			clientsTable.setItems(clientInfoList);
			clientsTable.refresh();
		}
	}
	
}
