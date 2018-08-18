package services.infoTown.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.security.MessageDigest;

import javax.imageio.ImageIO;



public class ImageGetter {
	
	private static final String PATH = "C:\\Users\\Jelena\\Desktop\\Jelena\\Faks\\III godina\\VI semestar\\MREZNO I DISTRIBUIRANO PROGRAMIRANJE\\projektni_zadatak\\Tourist Info\\src\\services\\infoTown\\client\\images\\";

	public static String saveImageFromURL(String urlString) {
		try {
			URL imageURL = new URL(urlString);
				String[] split = urlString.split("\\.");
				String extension = split[split.length - 1];
				BufferedImage bufferedImage = ImageIO.read(imageURL);
		        String name = getHashString(urlString);
		        if(!exists(name+"."+extension)) {
		        	File output = new File(PATH+name+"."+extension);
		        	ImageIO.write(bufferedImage, extension, output);
		        }
				return name+"."+extension;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	private static boolean exists(String fileName) {
		File images = new File(PATH);
		if(images.exists() && images.isDirectory()) {
		    for(File file : images.listFiles() ) {
		    	if(fileName.equals(file.getName()))
		    		return true;
		    }
		}
		return false;
	}
	
	private static String getHashString(String imageURL) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			byte[] hash = digest.digest(imageURL.getBytes());
			StringBuilder sb = new StringBuilder(hash.length * 2);
			for(byte b: hash)
			   sb.append(String.format("%02x", b));
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
