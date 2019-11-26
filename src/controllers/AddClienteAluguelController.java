package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import main.Principal;
import model.Cliente;
import persistence.ClienteDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddClienteAluguelController implements Initializable {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private JFXTextField nomeCliente, cpfCliente, emailCliente, telCliente;
    @FXML
    private void addCliente(){
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
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/AddAluguel.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        AlugarVeiculoController.principal.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
