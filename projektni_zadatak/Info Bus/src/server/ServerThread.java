package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import services.infoBus.model.BusLine;
import util.BusLineReader;
import util.LoggerWrapper;

public class ServerThread extends Thread{

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private ObjectOutputStream objectOutputStream;
	private LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
	
	public ServerThread(Socket socket) throws Exception{
		this.socket = socket;
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	}
	
	
	//klijent salje jednu od poruka: ALL LINES, DETAILS (+broj linije za koju hoce detalje), STATUS(+broj linije za koju hoce status) sve dok ne posalje END
	//ako je poruka dobra server prvo salje OK, pa onda trazeni sadrzaj, ako nije salje NOK i tu zavrsava komunikaciju
	@Override
	public void run() {
		try { 
		 while(true){
			String command= bufferedReader.readLine();
			if("ALL LINES".equals(command)) {
				printWriter.println("OK");
				
				objectOutputStream.writeObject(BusLineReader.getAll());
			}else if("DETAILS".equals(command)) {
				printWriter.println("OK");
				
			    String lineNum = bufferedReader.readLine();
			    List<BusLine> lines = BusLineReader.getAll();
			    for(BusLine line: lines) {
			    	if(line.getLineNum().equals(lineNum))
			    	  objectOutputStream.writeObject(line.getDepartures());
			    }     
			}else if("STATUS".equals(command)) {
				printWriter.println("OK");
				
				String lineNum = bufferedReader.readLine();
				List<BusLine> lines = BusLineReader.getAll();
				BusLine myLine = null;
			    for(BusLine line: lines) {
			    	if(line.getLineNum().equals(lineNum))
			    	 myLine = line;
			    }     
				
				Random random = new Random();
				int randomNum = random.nextInt(myLine.getDepartures().size());
				int i = 0;
				for(String station : myLine.getDepartures().keySet()) {
					if(i == randomNum) {
						printWriter.println("Dolazak na stanicu "+ station+ " je za " +randomNum+" minuta");
					}
					i++;
				}
			}else if(command.equals("END")){
				break;
			}else {
				printWriter.println("NOK");
			}
		 }
		}catch (Exception e) {
			// TODO: handle exception
			loggerWrapper.getLogger().log(Level.SEVERE, "Error happened while reading or writing to socket", e);
		}
	}
	
}
