package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import services.infoAdmin.client.InfoAdminClient;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/MainScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Tourist info");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
			primaryStage.show();
			
			InfoAdminClient infoAdminClient = new InfoAdminClient(primaryStage);
			infoAdminClient.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
