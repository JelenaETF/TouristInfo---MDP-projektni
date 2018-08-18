package services.infoHotel.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import services.infoHotel.HotelServiceInterface;

public class HotelClient {

	public static HotelServiceInterface getHotelService() {
		String policyPath ="C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Tourist Info\\src\\services\\infoHotel\\client\\client_policyfile.txt";
		System.setProperty("java.security.policy", policyPath);
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(1099);
			HotelServiceInterface serviceInterface = (HotelServiceInterface) registry.lookup("HotelService");
			return serviceInterface;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
