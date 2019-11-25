package controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Veiculo;

import java.net.URL;
import java.util.ResourceBundle;

public class AttVeiculoController implements Initializable {
    private enum Itens {MODELO, PLACA, ALUGUEL, VENDA, KM};
    @FXML
    private JFXComboBox cbItem;

    @FXML
    private void revelarEdit(){
        if (cbItem.getSelectionModel().getSelectedItem().equals(Itens.MODELO)){
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbItem.getItems().setAll(Itens.values());
    }
}
