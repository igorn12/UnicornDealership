package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Principal;
import model.Veiculo;
import persistence.VeiculoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private enum TIPO{MOTO, CARRO};
    private enum QUALIDADE{NOVO, SEMINOVO};

    @FXML
    private ImageView vehicleIcon;

    @FXML
    private JFXTextField kmRodados, placa, anoVeiculo, valorAluguel, valorVenda, modelo, descricao;

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
        if(cbQualidade.getSelectionModel().getSelectedItem().equals(QUALIDADE.SEMINOVO)){
            kmRodados.setVisible(true);
        }else if (cbQualidade.getSelectionModel().getSelectedItem().equals(QUALIDADE.NOVO)){
            kmRodados.setVisible(false);
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

        placa.setText(sb.toString());
    }

    @FXML
    private void addVeiculo(){
        if(placa.getText().isEmpty() || modelo.getText().isEmpty() || valorAluguel.getText().isEmpty() || valorVenda.getText().isEmpty() || anoVeiculo.getText().isEmpty() || descricao.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Os campos obrigatórios estão vazios");
            alert.setContentText("OS CAMPOS COM * SÃO OBRIGATÓRIOS");

            alert.showAndWait();
        }else{
            int ano = Integer.parseInt(anoVeiculo.getText());
            Double aluguel, venda, kms;
            aluguel = Double.valueOf(valorAluguel.getText());
            venda = Double.valueOf(valorVenda.getText());
            String tipo = String.valueOf(cbTipo.getSelectionModel().getSelectedItem());
            String desc = descricao.getText();
            if(cbQualidade.getSelectionModel().getSelectedItem().equals(QUALIDADE.NOVO)) {
                kms = 0.0;
            }else{
                kms = Double.valueOf(kmRodados.getText());
            }
            Veiculo v = new Veiculo(ano,tipo,modelo.getText(),placa.getText(), desc, kms, venda,aluguel);
            veiculoDAO.insertVeiculo(v);
        }
        limparCampos();
    }

    @FXML
    private void limparCampos(){
        placa.clear();
        cbTipo.getSelectionModel().clearSelection();
        cbQualidade.getSelectionModel().clearSelection();
        modelo.clear();
        valorVenda.clear();
        valorAluguel.clear();
        kmRodados.clear();
        descricao.clear();
        anoVeiculo.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipo.getItems().setAll(TIPO.values());
        cbQualidade.getItems().setAll(QUALIDADE.values());
    }
}
