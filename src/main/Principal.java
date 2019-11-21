package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    @FXML
    public static Stage principalStage;

    @Override
    public void start(Stage stage) throws Exception{
        principalStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        stage.setTitle("Unicorn Dealership");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
