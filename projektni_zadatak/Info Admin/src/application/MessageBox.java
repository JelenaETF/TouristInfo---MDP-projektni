package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    public static void display(String message) {

        Label label = new Label(message);
        VBox layout = new VBox(10); //spacing 10
        layout.setPadding(new Insets(40,40,40,40));
        layout.getChildren().add(label);

        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL); //ne mozemo raditi nista u pozadini
        stage.setScene(scene);
        stage.showAndWait();
    }

}
