package services.infoHotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import services.infoHotel.model.Hotel;
import serialization.GsonSerialization;
import serialization.JavaSerialization;
import serialization.KryoSerialization;
import util.HotelUtil;

public class HotelService extends UnicastRemoteObject implements HotelServiceInterface {

	public static String SERIALIZATION_TYPE;
	
	public HotelService() throws RemoteException {
		super();
	}

	@Override
	public List<Hotel> getAll() throws RemoteException {
		List<Hotel> hotels = new ArrayList<>();
		if(GsonSerialization.deserializeAll() == null || KryoSerialization.deserializeAll() == null || JavaSerialization.deserializeAll() == null)
			return null;
	   
		try {
			hotels.addAll(GsonSerialization.deserializeAll());
			hotels.addAll(KryoSerialization.deserializeAll());
			hotels.addAll(JavaSerialization.deserializeAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RemoteException("Internal server error!");
		}
	    return hotels;
	}

	@Override
	public int add(Hotel hotel) throws RemoteException {
		int id = HotelUtil.getLastUsedId();
		if(id != -2) {
		  id++;
		  hotel.setId(id);
		}
		
		if(!"gson".equals(SERIALIZATION_TYPE) && !"kryo".equals(SERIALIZATION_TYPE) && !"java".equals(SERIALIZATION_TYPE))
			throw new RemoteException("Unsuported serialization type!");
		
		try {		
			switch(SERIALIZATION_TYPE) {
				case "gson":{
					GsonSerialization.serializeHotel(hotel);
					break;
				}
				case "kryo":{
					KryoSerialization.serializeHotel(hotel);
					break;
				}
				case "java":{
					JavaSerialization.serializeHotel(hotel);
					break;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new RemoteException("Internal server error!");
		}
		return hotel.getId();
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		try {
			String mainPath = "resources"+File.separator+"serialized"+File.separator;
			Path filePath = null;
			if(GsonSerialization.deserializeHotel(id) != null) {
				filePath = Paths.get(mainPath+"gson"+File.separator+id+".json");
			}else if(KryoSerialization.deserializeHotel(id) != null) {
				filePath = Paths.get(mainPath+"kryo"+File.separator+id+".bin");
			}else if(JavaSerialization.deserialize(id) != null) {
				filePath = Paths.get(mainPath+"java"+File.separator+id+".java");
			}else {
				return false;
			}
			
			Files.delete(filePath);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File("resources"+File.separator+"serialization.properties")));
			SERIALIZATION_TYPE = properties.getProperty("serialization");
		} catch (Exception e) {
			System.out.println("Unable to find properties file!");
		}
		
		String policyPath = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Info Hotel\\resources\\server_policyfile.txt";
		//"resources"+File.separator+"server_policyfile.txt"
		System.setProperty("java.security.policy", policyPath );
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			HotelService service = new HotelService();
			UnicastRemoteObject.unexportObject(service, true);
			HotelServiceInterface stub = (HotelServiceInterface) UnicastRemoteObject.exportObject(service, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("HotelService", stub);
			System.out.println("Server started!");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
