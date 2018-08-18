package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import services.infoBus.client.BusLineClient;
import services.infoBus.model.BusLine;

public class BusLinesSceneController implements Initializable {

	@FXML
	private TableView<BusLine> busLinesTable;
	@FXML
	private TableColumn<String, BusLine> lineNum;
	@FXML
	private TableColumn<String, BusLine> from;
	@FXML
	private TableColumn<String, BusLine> to;

	private ObservableList<BusLine> busLines = FXCollections.observableArrayList(); 
	private static BusLineClient busLineClient = new BusLineClient();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		generateTable();
	}
	
	private void generateTable() {
		lineNum.setCellValueFactory(new PropertyValueFactory<>("lineNum"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		try {
			busLines.setAll(busLineClient.getAllLines());
			busLinesTable.setItems(busLines);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	public void openSceneForDetails() {
		try {
			BusLineDetailsSceneController.setLineToShowDetails(busLinesTable.getSelectionModel().getSelectedItem());
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/BusLineDetailsScene.fxml"));
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

	public static BusLineClient getBusLineClient() {
		return busLineClient;
	}

	public static void setBusLineClient(BusLineClient busLineClient) {
		BusLinesSceneController.busLineClient = busLineClient;
	}
	
	
	
}
