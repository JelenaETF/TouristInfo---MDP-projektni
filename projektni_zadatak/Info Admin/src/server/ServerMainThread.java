package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import model.TouristInfoClient;

public class ServerMainThread extends Thread{

	private static final int PORT = 1822;
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
