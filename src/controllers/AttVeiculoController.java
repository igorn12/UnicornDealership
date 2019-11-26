package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Principal;
import persistence.VeiculoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AttVeiculoController implements Initializable {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private enum Itens {MODELO,PLACA,ALUGUEL,VENDA};

    @FXML
    private JFXComboBox<Itens> cbItem;

    @FXML
    private JFXTextField txUpdate;

    @FXML
    private JFXButton btGerarPlaca;

    @FXML
    private void attVeiculo(){
        if(cbItem.getSelectionModel().getSelectedItem().equals(Itens.MODELO)){
            veiculoDAO.updateModelo(txUpdate.getText(), ToManageController.veiculo);
        }else if(cbItem.getSelectionModel().getSelectedItem().equals(Itens.PLACA)){
            veiculoDAO.updatePlaca(txUpdate.getText(), ToManageController.veiculo);
        }else if(cbItem.getSelectionModel().getSelectedItem().equals(Itens.ALUGUEL)){
            double aluguel = Double.valueOf(txUpdate.getText());
            veiculoDAO.updateAluguel(aluguel, ToManageController.veiculo);
        }else if(cbItem.getSelectionModel().getSelectedItem().equals(Itens.VENDA)){
            double venda = Double.valueOf(txUpdate.getText());
            veiculoDAO.updateVenda(venda, ToManageController.veiculo);
        }
    }

    @FXML
    private void mostrarBt(){
        if (cbItem.getSelectionModel().getSelectedItem().equals(Itens.PLACA)){
            btGerarPlaca.setVisible(true);
        }else{
            btGerarPlaca.setVisible(false);
        }
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

        txUpdate.setText(sb.toString());
    }

    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/ToManage.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        ToManageController.principal.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbItem.getItems().setAll(Itens.values());
    }
}
