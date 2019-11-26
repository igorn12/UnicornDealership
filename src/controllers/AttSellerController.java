package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Principal;
import persistence.VendedorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttSellerController implements Initializable {
    private VendedorDAO vendedorDAO = new VendedorDAO();

    private enum Itens{NOME, TELEFONE, SALARIO};

    @FXML
    private JFXComboBox<Itens> cbItens;

    @FXML
    private JFXTextField txAlteracao;

    @FXML
    private void voltar() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Sellers.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        SellersController.vendedor.close();
    }

    @FXML
    private void updateVendedor(){
        if (cbItens.getSelectionModel().getSelectedItem().equals(Itens.NOME)){
            vendedorDAO.updateNomeVendedor(txAlteracao.getText(), SellersController.v);
        }else if (cbItens.getSelectionModel().getSelectedItem().equals(Itens.SALARIO)){
            double salario = Double.valueOf(txAlteracao.getText());
            vendedorDAO.updateSalarioVendedor(salario, SellersController.v);
        }else if(cbItens.getSelectionModel().getSelectedItem().equals(Itens.TELEFONE)){
            vendedorDAO.updateTelVendedor(txAlteracao.getText(), SellersController.v);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbItens.getItems().setAll(Itens.values());
    }
}
