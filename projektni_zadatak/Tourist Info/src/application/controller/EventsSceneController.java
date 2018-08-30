package application.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import application.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import services.infoEvent.client.EventClient;
import services.infoEvent.model.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventsSceneController implements Initializable{

	
	@FXML
	private TableView<Event> eventsTable;
	@FXML
	private TableColumn<String, Event> name;
	@FXML
	private TableColumn<LocalDateTime, Event> dateTime;
	@FXML
	private TableColumn<String, Event> location;
	@FXML
	private TableColumn<EventCategory, Event> category;
	@FXML
	private TableColumn<String, Event> description;
	
	@FXML
	private ComboBox<String> searchParameterComboBox;	
	@FXML
	private ComboBox<String> locationComboBox;
	@FXML
	private ComboBox<String> categoryComboBox;
	@FXML
	private DatePicker firstDatePicker;
	@FXML
	private DatePicker secondDatePicker;
	@FXML
	private Button confirmSearchButton;
	
	private ObservableList<Event> eventsList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchParameterComboBox.getItems().addAll("kategorija", "datum", "period", "lokacija");
		generateTable();
	}
	
	private void generateTable() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		location.setCellValueFactory(new PropertyValueFactory<>("location"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
	}
	
	@FXML
	public void makeSearch() {        //kad se odabere jedan od kriterijuma pretrage
		String searchCriterium = searchParameterComboBox.getSelectionModel().getSelectedItem();
		switch(searchCriterium) {
			case "kategorija":{
				firstDatePicker.setVisible(false);
				secondDatePicker.setVisible(false);
				confirmSearchButton.setVisible(false);
				locationComboBox.setVisible(false);
				categoryComboBox.setVisible(true);
				categoryComboBox.getItems().clear();
			   for(EventCategory category : EventCategory.values()) {
				  categoryComboBox.getItems().add(category.toString());
			   }
			   break;
			}
			case "lokacija":{
				firstDatePicker.setVisible(false);
				secondDatePicker.setVisible(false);
				confirmSearchButton.setVisible(false);
				categoryComboBox.setVisible(false);
				locationComboBox.setVisible(true);
				locationComboBox.getItems().clear();
				List<String> locations = EventClient.getAllLocations();
				if(locations != null) {
					Set<String> locationsSet = new HashSet<>(locations);
				    locationComboBox.getItems().addAll(locationsSet);
				}else
					MessageBox.display("Problem pri dohvatanju podataka sa servera!");
				break;
			}
			case "datum":{
				secondDatePicker.setVisible(false);
				categoryComboBox.setVisible(false);
				locationComboBox.setVisible(false);
				firstDatePicker.setVisible(true);
				firstDatePicker.setValue(null);
				confirmSearchButton.setVisible(true);
				break;
			}
			case "period":{
				categoryComboBox.setVisible(false);
				locationComboBox.setVisible(false);
				firstDatePicker.setVisible(true);
				firstDatePicker.setValue(null);
				secondDatePicker.setVisible(true);
				secondDatePicker.setValue(null);
				confirmSearchButton.setVisible(true);
				break;
			}
		}
	}
	
	@FXML
	public void showTableForLocation() {
		String location = locationComboBox.getSelectionModel().getSelectedItem();
		List<Event> events = EventClient.getAllByLocation(location);
		if(events != null) {
			eventsList.removeAll(eventsList);
			eventsList.addAll(events);
			eventsTable.setItems(eventsList);
			eventsTable.refresh();
		}
	}
	
	@FXML
	public void showTableForCategory() {
		EventCategory category = EventCategory.valueOf(categoryComboBox.getSelectionModel().getSelectedItem());
		List<Event> events = EventClient.getAllByCategory(category);
		if(events != null) {
			eventsList.removeAll(eventsList);
			eventsList.addAll(events);
			eventsTable.setItems(eventsList);
			eventsTable.refresh();
		}
	}
	
	@FXML
	public void showTableForDateOrPeriod() {
		if(!secondDatePicker.isVisible()) {      	//radi se o pretrazi po tacnom datumu
			if(firstDatePicker.getValue() == null) {
				MessageBox.display("Morate odabrati datum pretrage!");
			}else {
				List<Event> eventsByDate = EventClient.getAllByDate(firstDatePicker.getValue().toString());
				if(eventsByDate != null) {
					eventsList.removeAll(eventsList);
					eventsList.addAll(eventsByDate);
					eventsTable.setItems(eventsList);
					eventsTable.refresh();
				}
			}
		}else {										//pretraga po periodu
			if(firstDatePicker.getValue() == null || secondDatePicker.getValue() == null) {
				MessageBox.display("Morate unijeti oba datuma za pretragu po periodu!");
			}else {
				List<Event> eventsByPeriod = EventClient.getAllByPeriod(firstDatePicker.getValue().toString(), secondDatePicker.getValue().toString());
				if(eventsByPeriod != null) {
					eventsList.removeAll(eventsList);
					eventsList.addAll(eventsByPeriod);
					eventsTable.setItems(eventsList);
					eventsTable.refresh();
				}
			}
		}
	}
	
}
