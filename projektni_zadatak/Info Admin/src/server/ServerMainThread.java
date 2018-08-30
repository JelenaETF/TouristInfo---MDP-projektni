package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import application.Main;
import model.TouristInfoClient;
import util.LoggerWrapper;

public class ServerMainThread extends Thread{

	private static final int PORT = 1822;
	private LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			Socket clientSocket = null;
			
			while(true) {
				clientSocket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(clientSocket);
				serverThread.start();
			}
		} catch (Exception e) {
			loggerWrapper.getLogger().log(Level.SEVERE, "Unable to communicate with Tourist Info client", e);
		}
	}

}
