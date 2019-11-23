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

public class RemoveController implements Initializable {
    @FXML
    private void deleteSeller(){

    }

    @FXML
    private void voltar() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Seller.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        SellersController.principal.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
