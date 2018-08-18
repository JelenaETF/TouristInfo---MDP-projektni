package service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Event;
import model.EventCategory;
import repository.EventRepository;

@Path("/events")
public class EventService {

	private EventRepository eventRepository = new EventRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Event> events = eventRepository.getAll();
		if( events == null)
			return Response.status(500).entity("Unable to get events!").build();
		else {
			return Response.status(200).entity(events).build();
		}
	}
	
	@Path("/category/{category}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByCategory(@PathParam ("category") EventCategory category) {
		List<Event> eventsByKategory = eventRepository.getEventsByCategory(category);
		if( eventsByKategory == null )
			return Response.status(500).entity("Unable to get events!").build();
		else
			return Response.status(200).entity(eventsByKategory).build();
	}
	
	@Path("/date/{date}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByDate(@PathParam ("date") String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(date, dateTimeFormatter);
		}catch (Exception e) {
			// TODO: handle exception
			return Response.status(400).entity("Illegal date format!").build();
		}
		
		List<Event> eventsByDate = eventRepository.getEventsByDate(localDate);
		if( eventsByDate == null )
			return Response.status(500).entity("Unable to get events!").build();
		else
			return Response.status(200).entity(eventsByDate).build();
	}
	
	@Path("/period/{dateFrom}/{dateTo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBetweenDates(@PathParam ("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDateFrom, localDateTo;
		try {
		    localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
		    localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
		}catch (Exception e) {
			// TODO: handle exception
			return Response.status(400).entity("Illegal date format!").build();
		}
		
		List<Event> eventsBetweenDates = eventRepository.getEventsBetweenDates(localDateFrom, localDateTo);
		if( eventsBetweenDates == null )
			return Response.status(500).entity("Unable to get events!").build();
		else
			return Response.status(200).entity(eventsBetweenDates).build();
	}
	
	@Path("/location/{location}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByLocation(@PathParam ("location") String location) {
		String decodedLocation;
		try {
			decodedLocation = URLDecoder.decode(location, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			decodedLocation = location;
		}
		
		List<Event> eventsByLocation = eventRepository.getEventsByLocation(decodedLocation);
		if( eventsByLocation == null )
			return Response.status(500).entity("Unable to get events!").build();
		else
			return Response.status(200).entity(eventsByLocation).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Event event) {
		int id = eventRepository.add(event);
	    if(id == -1)
			return Response.status(400).entity("Illegal argumant passed!").build();
		else if(id == 0) 
			return Response.status(500).entity("Unable to get events!").build();
		else {
			return Response.status(200).entity(id).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancel(Event event) {
		if(eventRepository.cancel(event.getId()))
			return Response.status(200).entity("Successffully canceled event!").build();
		else
			return Response.status(400).entity("Illegal argument passed!").build();
	}
}
