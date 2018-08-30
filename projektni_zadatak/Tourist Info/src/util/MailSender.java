package util;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
    
	private static LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	private static final String USERNAME = "matija.matijevic1869@gmail.com";
	private static final String PASSWORD = "matija1869";
	
	public static void send(String messageToSend) {
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
			InternetAddress from = new InternetAddress(USERNAME, "Matija Matijevic");
			InternetAddress to = new InternetAddress("smiljanic.jelena882@gmail.com");
			
			message.setFrom(from);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject("Problem u komunikaciji izmedju aplikacija");
			message.setText(messageToSend);
			Transport.send(message);
		}catch (Exception e) {
			// TODO: handle exception
			loggerWrapper.getLogger().log(Level.SEVERE, "Problem with mail sending", e);
		}
	}
	
}
