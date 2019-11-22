package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.Principal;
import persistence.VendedorDAO;

import java.io.IOException;

public class AddSellerController {
    private VendedorDAO vendedorDAO = new VendedorDAO();

    @FXML
    private Label labelConfirm;

    @FXML
    private JFXTextField nomeVendedor, cpfVendedor, telVendedor, salarioVendedor;

    @FXML
    private void addVendedor(){
        if (nomeVendedor.getText().isEmpty() || cpfVendedor.getText().isEmpty() || salarioVendedor.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Os campos obrigatórios estão vazios");
            alert.setContentText("Os campos obrigatórios estão vazios");

            alert.showAndWait();
        }else{
            double salario = Double.parseDouble(salarioVendedor.getText());
            vendedorDAO.insertVendedor(nomeVendedor.getText(), cpfVendedor.getText(), telVendedor.getText(), salario);
            labelConfirm.setVisible(true);
        }
    }
    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }
}
