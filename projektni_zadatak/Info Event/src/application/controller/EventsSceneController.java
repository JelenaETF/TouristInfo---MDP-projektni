package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import application.MessageBox;
import client.EventClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;
import model.EventCategory;

public class EventsSceneController implements Initializable{

	@FXML
	private TableView<Event> eventsTable;
	@FXML
	private TableColumn<String, Event> nameColumn;
	@FXML
	private TableColumn<LocalDateTime, Event> dateTimeColumn;
	@FXML
	private TableColumn<String, Event> locationColumn;
	@FXML
	private TableColumn<EventCategory, Event> categoryColumn;
	@FXML
	private TableColumn<String, Event> descriptionColumn;
	
	@FXML
	private TextField name;
	@FXML
	private DatePicker date;
	@FXML
	private TextField hour;
	@FXML 
	private TextField minutes;
	@FXML
	private TextField location;
	@FXML
	private ComboBox<EventCategory> category;
	@FXML
	private TextArea description;
	
	private ObservableList<Event> eventsList = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		category.getItems().addAll(EventCategory.values());
		generateTable();
	}

	private void generateTable() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		List<Event> events = EventClient.getAll();
		if(events == null) {
			MessageBox.display("Greska pri dobijanju podataka sa servera!");
		}
		eventsList.addAll(events);
		eventsTable.setItems(eventsList);
	}
	
    public void cancel() {
    	Event eventToCancel = eventsTable.getSelectionModel().getSelectedItem();
    	if(eventToCancel == null)
    		MessageBox.display("Morate odabrati dogadjaj!");
    	else {
    		if(EventClient.cancelEvent(eventToCancel)) {
    			MessageBox.display("Dogadjaj uspjesno otkazan");
    			eventsList.remove(eventToCancel);
    			eventsTable.setItems(eventsList);
    			eventsTable.refresh();
    		}
    		else
    			MessageBox.display("Greska na serverskoj strani");
    	}
    }
    
    public void add() {
    	if(name.getText() == null || description.getText() == null || location.getText() == null || date.getValue() == null
    			|| hour.getText() == null || minutes.getText() == null || category.getSelectionModel().getSelectedItem() == null) {
    		MessageBox.display("Nijedno polje ne smije ostati prazno!");
    	}else if(!regularDate(date.getValue())) {
    		MessageBox.display("Uneseni datum nije regularan!");
    	} else if(!regularHourAndMinutes(hour.getText(), minutes.getText())) {
    		MessageBox.display("Uneseno vrijeme nije regularno!");
    	} else {
    		LocalTime time = LocalTime.of(Integer.parseInt(hour.getText()), Integer.parseInt(minutes.getText()));
    		LocalDateTime dateTime = LocalDateTime.of(date.getValue(), time);
    		
    		Event newEvent = new Event();
    		newEvent.setName(name.getText());
    		newEvent.setLocation(location.getText());
    		newEvent.setDescription(description.getText());
    		newEvent.setCanceled(false);
    		newEvent.setCategory(category.getSelectionModel().getSelectedItem());
    		newEvent.setDateTime(dateTime);
    		
    		int id = EventClient.add(newEvent);
    		newEvent.setId(id);
    		MessageBox.display("Uspjesno dodano!");
    		name.setText("");
    		description.setText("");
    		location.setText("");
    		hour.setText("");
    		minutes.setText("");
    		date.setValue(null);
    		category.getSelectionModel().clearSelection();
    		
    		eventsList.add(newEvent);
    		eventsTable.setItems(eventsList);
    		eventsTable.refresh();
    		
    	}
    }
    
    private boolean regularHourAndMinutes(String hour, String minutes) {
    	try {
    		int hourInt = Integer.parseInt(hour);
    		int minutesInt = Integer.parseInt(minutes);
    		if(hourInt < 0 || hourInt > 23) return false;
    		if(minutesInt < 0 || minutesInt > 59) return false;
    	}catch (Exception e) {
			// TODO: handle exception
    		return false;
		}
    	return true;
    }
    
    private boolean regularDate(LocalDate date) {
    	return date.isAfter(LocalDate.now());
    }
}
