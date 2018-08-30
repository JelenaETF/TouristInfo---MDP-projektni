package services.infoWeather.client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import util.MailSender;

public class MulticastClient {

	public static final String IP_ADDRESS = "224.0.0.11";
	public static final int PORT = 1820;
	
	public static String getWeatherInfo() {
		String weatherInfo = null;
		try {
			MulticastSocket multicastSocket = new MulticastSocket(PORT);
			InetAddress inetAddress = InetAddress.getByName(IP_ADDRESS);
			multicastSocket.joinGroup(inetAddress);
			
			byte[] buffer = new byte[256];
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			multicastSocket.receive(datagramPacket);
			weatherInfo = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			return weatherInfo;
		}catch (Exception e) {
			MailSender.send("Problem u komunikaciji sa aplikacijom Info Weather!");
			return null;
		}
	} 
}
