package server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import application.controller.MainSceneController;
import javafx.scene.control.TableView;
import model.TouristInfoClient;

public class ConnectionTestThread extends Thread{

	private Socket socket;
	private TouristInfoClient client;
	private static TableView<TouristInfoClient> tableToUpdate;
	private static final String USERNAME = "matija.matijevic1869";
	private static final String PASSWORD = "matija1869";
	
	public ConnectionTestThread(Socket socket, TouristInfoClient client) {
		this.socket = socket;
		this.client = client;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if(isExternalyClosed() && !client.isTerminated()) {
				MainSceneController.getClientInfoList().remove(client);
				tableToUpdate.setItems(MainSceneController.getClientInfoList());
				tableToUpdate.refresh();
				sendMail();
				break;
			}else {
				try {
					sleep(500);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	private void sendMail() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(new File("resources/mail.properties")));
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			});
			
			Message message = new MimeMessage(session);
			InternetAddress from = new InternetAddress("matija.matijevic1869@gmail.com","Matija Matijevic");
			InternetAddress to = new InternetAddress("smiljanic.jelena882@gmail.com");
		
			message.setSubject("Informacije o neovlastenom gasenju Tourist Info aplikacije");
			message.setFrom(from);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setText("Aplikacija Tourist Info na adresi "+client.getIpAddress()+":"+client.getPort());
			Transport.send(message);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean isExternalyClosed() {
		try {
			return socket.getInputStream().read() == -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return true;
		}
	}

	public static TableView<TouristInfoClient> getTableToUpdate() {
		return tableToUpdate;
	}

	public static void setTableToUpdate(TableView<TouristInfoClient> tableToUpdate) {
		ConnectionTestThread.tableToUpdate = tableToUpdate;
	}
}
