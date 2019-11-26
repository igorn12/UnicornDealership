package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import persistence.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;

public class AddAluguelController implements Initializable {
    public static Stage stageAluguel;

    private ClienteDAO clienteDAO = new ClienteDAO();

    private ObservableList<Cliente> listCliente = FXCollections.observableArrayList();

    @FXML
    private JFXDatePicker dtInicio, dtEntrega;

    @FXML
    private JFXComboBox<Cliente> cbClientes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshClientes();
        if(cbClientes.getItems().isEmpty()){
            Parent addCliente = null;
            try {
                addCliente = FXMLLoader.load(getClass().getResource("/view/AddCliente.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Adicionar Cliente");
            stage.setScene(new Scene(addCliente));
            stage.show();
            stageAluguel = stage;
        }
    }

    private void refreshClientes(){
        listCliente.clear();
        listCliente.addAll(clienteDAO.listNomeCliente());
        cbClientes.getItems().setAll(listCliente);
    }

    @FXML
    private void addAluguel(){

    }
}
