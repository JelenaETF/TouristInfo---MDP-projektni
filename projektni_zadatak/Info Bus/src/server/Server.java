package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import util.BusLineCreator;

public class Server {
    
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
			e.printStackTrace();
		}
	}
}
