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
    private void register() throws IOException{
        Parent register = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        Principal.principalStage.setScene(new Scene(register));
    }
    @FXML
    private void toManage() throws IOException {
        Parent remove = FXMLLoader.load(getClass().getResource("/view/toManage.fxml"));
        Principal.principalStage.setScene(new Scene(remove));
    }
    @FXML
    private void sellers() throws IOException{
        Parent sell = FXMLLoader.load(getClass().getResource("/view/Sellers.fxml"));
        Principal.principalStage.setScene(new Scene(sell));
    }
    @FXML
    private void rent() throws IOException{
        Parent yield = FXMLLoader.load(getClass().getResource("/view/Rent.fxml"));
        Principal.principalStage.setScene(new Scene(yield));
    }
    @FXML
    private void out(){
        Principal.principalStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
