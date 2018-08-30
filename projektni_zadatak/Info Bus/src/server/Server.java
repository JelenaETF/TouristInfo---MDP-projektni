package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

import util.BusLineCreator;
import util.LoggerWrapper;

public class Server {
    
	private static LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	public static void main(String[] args) {
		try {
			BusLineCreator.getInstance();
			ServerSocket serverSocket = new ServerSocket(1818);
			Socket clientSocket;
			while(true) {
				clientSocket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(clientSocket);
				serverThread.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			loggerWrapper.getLogger().log(Level.SEVERE, "Unable to communicate with Tourist Info", e);
		}
	}
}
