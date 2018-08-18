package services.infoTown.client;

import java.util.ArrayList;
import java.util.List;

import services.infoTown.model.TouristAttraction;
import services.infoTown.service.TouristAttractionService;
import services.infoTown.service.TouristAttractionServiceServiceLocator;

public class InfoTownClient {

	public static List<TouristAttraction> getTouristAttractions(){
		try {
			TouristAttractionServiceServiceLocator locator = new TouristAttractionServiceServiceLocator();
			TouristAttractionService service = locator.getTouristAttractionService();
			TouristAttraction[] attractions = service.getAll();
		
			List<TouristAttraction> attractionsAsList = new ArrayList<>();
			for(TouristAttraction attraction : attractions) {
				attractionsAsList.add(attraction);
			}
			return attractionsAsList;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
