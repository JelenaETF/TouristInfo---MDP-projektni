package services.infoBus.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import services.infoBus.model.BusLine;
import util.MailSender;

public class BusLineClient {
    
	private Socket socket;
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	private ObjectInputStream objectInputStream;
	
	public BusLineClient() {
		try {
			socket = new Socket("127.0.0.1", 1818);
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MailSender.send("Problem u komunikaciji sa aplikacijom Info Bus!");
			e.printStackTrace();
		} 
	}
	
   public List<BusLine> getAllLines() throws Exception {
	  List<BusLine> lines = null; 
	  printWriter.println("ALL LINES");
	  if("OK".equals(bufferedReader.readLine())) {
	    	 lines = (List<BusLine>)objectInputStream.readObject();
	  }else
	    return null;
	  return lines;
   }
   
   public HashMap<String, LocalTime> getDetailsForLine(String lineNum) throws Exception{
	   HashMap<String, LocalTime> departures = null;
	   printWriter.println("DETAILS");
		  if("OK".equals(bufferedReader.readLine())) {
			printWriter.println(lineNum);
		    departures = (HashMap<String, LocalTime>)objectInputStream.readObject();
		  }else
		    return null;
	   return departures;
   }
   
   public String getStatusForLine(String lineNum) throws Exception{
	   String status = null;
	   printWriter.println("STATUS");
		  if("OK".equals(bufferedReader.readLine())) {
			printWriter.println(lineNum);
		    status = bufferedReader.readLine();
		  }else
		    return null;
	   return status;
   }
   
   public void endConversation() {
	   printWriter.println("END");
   }
}
