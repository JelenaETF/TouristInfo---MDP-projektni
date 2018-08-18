package server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import rest.client.WeatherAPIClient;
import rest.model.Weather;

public class ServerMain {

	private static final String IP_ADDRESS = "224.0.0.11";
	private static final int PORT = 1818;
	
	public static void main(String[] args) {
		try {
			MulticastSocket multicastSocket = new MulticastSocket();
			InetAddress inetAddress = InetAddress.getByName(IP_ADDRESS);
			multicastSocket.joinGroup(inetAddress);
			
			DatagramPacket datagramPacket;
			Weather weather = null;;
			int counter = 0;
			while(true) {
				if(counter == 0) {
				   weather = WeatherAPIClient.getWeatherInfo();
				}
				datagramPacket = new DatagramPacket(weather.toString().getBytes(), weather.toString().getBytes().length, inetAddress, PORT);
				multicastSocket.send(datagramPacket);
				Thread.sleep(1000);
				
				counter++;
				if(counter == 11)
					counter = 0;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
