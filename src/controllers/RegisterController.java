package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Principal;
import persistence.AutomovelDAO;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private AutomovelDAO veiculoDAO = new AutomovelDAO();


    private enum TIPO{MOTO, CARRO};
    private enum QUALIDADE{NOVO, SEMINOVO};

    @FXML
    private ImageView vehicleIcon;

    @FXML
    private JFXTextField kmRodados, placa;

    @FXML
    private JFXComboBox<TIPO> cbTipo;

    @FXML
    private JFXComboBox<QUALIDADE> cbQualidade;

    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    @FXML
    private void carregarImagem(){
        if (cbTipo.getSelectionModel().getSelectedItem().equals(TIPO.MOTO))
            vehicleIcon.setImage(new Image("/view/img/motoIcon.png"));
        if (cbTipo.getSelectionModel().getSelectedItem().equals(TIPO.CARRO))
            vehicleIcon.setImage(new Image("/view/img/carroIcon.png"));
    }
    @FXML
    private void txField(){
        if(cbQualidade.getSelectionModel().getSelectedItem().equals(QUALIDADE.SEMINOVO))
            kmRodados.setVisible(true);
        if (cbQualidade.getSelectionModel().getSelectedItem().equals(QUALIDADE.NOVO))
            kmRodados.setVisible(false);
    }
    @FXML
    private void gerarPlaca(){
        Random rand = new Random();
        char[] numeros = "0123456789".toCharArray();
        char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 3; i++) {
            int ch = rand.nextInt (letras.length);
            sb.append (letras[ch]);
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            int n = rand.nextInt (numeros.length);
            sb.append (numeros[n]);
        }

        placa.setText(sb.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipo.getItems().setAll(TIPO.values());
        cbQualidade.getItems().setAll(QUALIDADE.values());
    }
}
