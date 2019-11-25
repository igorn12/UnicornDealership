package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Cliente;
import persistence.ClienteDAO;
import persistence.VeiculoDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class RentVeiculoController implements Initializable {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<Cliente> cbNomes;

    @FXML
    private JFXTextField txFieldAluguel;

    private void refreshClientes(){
        clientes.addAll(clienteDAO.listNomeCliente());
        cbNomes.setItems(clientes);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshClientes();

    }
}
