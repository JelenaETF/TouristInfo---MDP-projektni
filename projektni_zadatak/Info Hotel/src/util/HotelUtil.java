package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import services.infoHotel.model.Hotel;
import serialization.GsonSerialization;
import serialization.JavaSerialization;
import serialization.KryoSerialization;

public class HotelUtil {

	public static int getLastUsedId() {
		List<Hotel> hotels = new ArrayList<>();
		
		if(GsonSerialization.deserializeAll() == null || JavaSerialization.deserializeAll() == null || KryoSerialization.deserializeAll() == null)
			return -2;
		
		hotels.addAll(GsonSerialization.deserializeAll());
		hotels.addAll(JavaSerialization.deserializeAll());
		hotels.addAll(KryoSerialization.deserializeAll());
		
		if(hotels.isEmpty())
			return -1;
		
		return Collections.max(hotels).getId();
	}
}
