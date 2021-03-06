package application;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.ServerMainThread;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/MainScene.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Admin info");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:resources/admin_icon.png"));
		primaryStage.show();
		
		ServerMainThread serverMain = new ServerMainThread();
		serverMain.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
