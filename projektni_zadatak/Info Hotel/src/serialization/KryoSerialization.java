package serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import services.infoHotel.model.Hotel;

public class KryoSerialization {
      
	private static Kryo kryo;
	
	static {
		kryo = new Kryo();
		kryo.register(Hotel.class);
	}
	
	public static void serializeHotel(Hotel hotel) throws Exception{
		String path = "resources"+File.separator+"serialized"+File.separator+"kryo"+File.separator+ hotel.getId()+".bin";
		Output output = new Output(new FileOutputStream(new File(path)));
		kryo.writeObject(output, hotel);
		output.close();
	}
	
	public static Hotel deserializeHotel(int id) {
		Hotel hotel = null;
		try {
			String path = "resources"+File.separator+"serialized"+File.separator+"kryo"+File.separator+ id +".bin";
			Input input = new Input(new FileInputStream(new File(path))); 
			hotel = kryo.readObject(input, Hotel.class);
			input.close();
			return hotel;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static List<Hotel> deserializeAll(){
		try {
			String path = "resources"+File.separator+"serialized"+File.separator+"kryo";
			File kryoDir = new File(path);
			List<Hotel> kryoHotels = new ArrayList<>();
			Input input = null;
		
			for(File file : kryoDir.listFiles()) {
				input = new Input(new FileInputStream(file));
				kryoHotels.add(kryo.readObject(input, Hotel.class));
				input.close();
			}
		
			return kryoHotels;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
