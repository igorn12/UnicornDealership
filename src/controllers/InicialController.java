package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicialController implements Initializable {
    @FXML
    private void register() throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        Principal.principalStage.setScene(new Scene(register));
    }
    @FXML
    private void update() throws IOException {
        Parent remove = FXMLLoader.load(getClass().getResource("/view/Update.fxml"));
        Principal.principalStage.setScene(new Scene(remove));
    }
    @FXML
    private void rent() throws IOException{
        Parent rent = FXMLLoader.load(getClass().getResource("/view/Rent.fxml"));
        Principal.principalStage.setScene(new Scene(rent));
    }
    @FXML
    private void sell() throws IOException{
        Parent sell = FXMLLoader.load(getClass().getResource("/view/Sell.fxml"));
        Principal.principalStage.setScene(new Scene(sell));
    }
    @FXML
    private void addSeller() throws IOException{
        Parent addSeller = FXMLLoader.load(getClass().getResource("/view/AddSeller.fxml"));
        Principal.principalStage.setScene(new Scene(addSeller));
    }
    @FXML
    private void yield() throws IOException{
        Parent yield = FXMLLoader.load(getClass().getResource("/view/Yield.fxml"));
        Principal.principalStage.setScene(new Scene(yield));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
