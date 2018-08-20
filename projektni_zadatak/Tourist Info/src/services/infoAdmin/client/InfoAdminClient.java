package services.infoAdmin.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


import application.Main;
import application.controller.BusLinesSceneController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class InfoAdminClient extends Thread{

	private static final int PORT = 1822;
	private Socket socket;
	private BufferedReader bufferedReader;
	private Stage mainStage;
	
	public InfoAdminClient(Stage mainStage) throws Exception {
		socket = new Socket("127.0.0.1", PORT);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.mainStage = mainStage;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
	          String command = bufferedReader.readLine();
	          if("SHUT DOWN".equals(command)) {
	        	 Platform.runLater(() -> {
	        	 if(BusLinesSceneController.getBusLineClient() != null) {
	        		 mainStage.setOnHiding(e -> BusLinesSceneController.getBusLineClient().endConversation());
	        	 }
	        	 mainStage.close();
	        	 }
	        	 ); 
	        	 break;
	          }
	  		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
