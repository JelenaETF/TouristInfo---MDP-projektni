package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import services.infoHotel.model.Hotel;

public class JavaSerialization {
	
	public static void serializeHotel(Hotel hotel) throws Exception{
		String path = "resources"+File.separator+"serialized"+File.separator+"java"+File.separator+ hotel.getId()+".ser";
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
		objectOutputStream.writeObject(hotel);
		objectOutputStream.close();
	}
    
	public static Hotel deserialize(int id) {
		try {
			String path = "resources"+File.separator+"serialized"+File.separator+"gson"+File.separator+ id +".json";
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path)));
			Hotel hotel = (Hotel)objectInputStream.readObject();
			objectInputStream.close();
			return hotel;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static List<Hotel> deserializeAll(){
		try {
			List<Hotel> javaHotels = new ArrayList<>();
			String path = "resources"+File.separator+"serialized"+File.separator+"java";
			File javaDir = new File(path);
			ObjectInputStream objectInputStream = null;
		
			for(File file : javaDir.listFiles()) {
				objectInputStream = new ObjectInputStream(new FileInputStream(file));
				javaHotels.add((Hotel)objectInputStream.readObject());
				objectInputStream.close();
			}
		
			return javaHotels;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
