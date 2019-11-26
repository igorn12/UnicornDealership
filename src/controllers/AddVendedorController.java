package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.Principal;
import model.Vendedor;
import persistence.VendedorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddVendedorController implements Initializable {
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
            alert.setContentText("OS CAMPOS COM * SÃO OBRIGATÓRIOS");

            alert.showAndWait();
        }else{
            if(vendedorDAO.validaVendedor(nomeVendedor.getText()) && vendedorDAO.validaCpfVendedor(cpfVendedor.getText())) {
                double salario = Double.parseDouble(salarioVendedor.getText());
                Vendedor v = new Vendedor(nomeVendedor.getText(), cpfVendedor.getText(), telVendedor.getText(), salario);
                vendedorDAO.insertVendedor(v);
                labelConfirm.setVisible(true);
                limparCampos();
            }else {
                if(!vendedorDAO.validaVendedor(nomeVendedor.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atenção");
                    alert.setHeaderText("Nome do vendedor está repetido");
                    alert.setContentText("Esse vendedor já está cadastrado");

                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atenção");
                    alert.setHeaderText("CPF do vendedor está repetido");
                    alert.setContentText("Esse vendedor já está cadastrado");

                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    public void limparCampos() {
        nomeVendedor.clear();
        cpfVendedor.clear();
        telVendedor.clear();
        salarioVendedor.clear();
    }

    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Vendedores.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        VendedoresController.vendedor.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
