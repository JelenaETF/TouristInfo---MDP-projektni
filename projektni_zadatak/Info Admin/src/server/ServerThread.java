package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

import org.w3c.dom.css.ElementCSSInlineStyle;

import application.controller.MainSceneController;
import javafx.scene.control.TableView;
import model.TouristInfoClient;

public class ServerThread extends Thread{
   
	private Socket socket;
	private PrintWriter printWriter;
	private TouristInfoClient myClient;
	private static TableView<TouristInfoClient> tableToUpdate;
	
	public ServerThread(Socket socket) throws Exception {
		this.socket = socket;
		myClient = new TouristInfoClient(socket.getInetAddress().getHostAddress(), socket.getPort(), LocalTime.now());
		printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
		MainSceneController.getClientInfoList().add(myClient);
		tableToUpdate.setItems(MainSceneController.getClientInfoList());
		tableToUpdate.refresh();
		
	    ConnectionTestThread connectionTestThread = new ConnectionTestThread(socket, myClient);
	    connectionTestThread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			if(myClient.isTerminated()) {
				printWriter.println("SHUT DOWN");
				break;
			}else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static TableView<TouristInfoClient> getTableToUpdate() {
		return tableToUpdate;
	}

	public static void setTableToUpdate(TableView<TouristInfoClient> tableToUpdate) {
		ServerThread.tableToUpdate = tableToUpdate;
	}
	
}
