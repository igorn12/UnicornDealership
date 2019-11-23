package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellersController implements Initializable {
    @FXML
    public static Stage principal;
    @FXML
    private void addSeller() throws Exception{
         Parent addSeller = FXMLLoader.load(getClass().getResource("/view/AddSeller.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(addSeller));
         stage.setTitle("Adicionar Vendedor");
         stage.show();
         principal = stage;
    }
    @FXML
    private void attSeller() throws Exception{
        Parent attSeller = FXMLLoader.load(getClass().getResource("/view/attSeller.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(attSeller));
        stage.setTitle("Atualizar Vendedor");
        stage.show();
        principal = stage;
    }
    @FXML
    private void removeSeller() throws Exception{
        Parent removeSeller = FXMLLoader.load(getClass().getResource("/view/removeSeller.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(removeSeller));
        stage.setTitle("Remover Vendedor");
        stage.show();
        principal = stage;
    }
    @FXML
    private void voltar() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
