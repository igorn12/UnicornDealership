package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import model.Cliente;
import persistence.ClienteDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddClienteController implements Initializable{
    private ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private JFXTextField nomeCliente, cpfCliente, emailCliente, telCliente;

    @FXML
    public void addCliente(){
        if(nomeCliente.getText().isEmpty() || cpfCliente.getText().isEmpty() ||
                emailCliente.getText().isEmpty() || telCliente.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Os campos obrigatórios estão vazios");
            alert.setContentText("OS CAMPOS COM * SÃO OBRIGATÓRIOS");

            alert.showAndWait();
        }else{
            Cliente c = new Cliente(nomeCliente.getText(), cpfCliente.getText(), emailCliente.getText(), telCliente.getText());
            clienteDAO.insertCliente(c);
            limparCampos();
        }
    }

    private void limparCampos(){
        nomeCliente.clear();
        cpfCliente.clear();
        emailCliente.clear();
        telCliente.clear();
    }

    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
