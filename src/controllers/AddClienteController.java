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
    private Label labelConfirm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void addCliente() {
        if(nomeCliente.getText().isEmpty() || cpfCliente.getText().isEmpty() ||
                emailCliente.getText().isEmpty() || telCliente.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Os campos obrigatórios estão vazios");
            alert.setContentText("OS CAMPOS COM * SÃO OBRIGATÓRIOS");

            alert.showAndWait();
        }else{
            if(clienteDAO.validaCpfCliente(cpfCliente.getText())){
                Cliente c = new Cliente(nomeCliente.getText(), cpfCliente.getText(), emailCliente.getText(), telCliente.getText());
                clienteDAO.insertCliente(c);
                labelConfirm.setVisible(true);
                limparCampos();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atenção");
                alert.setHeaderText("Cliente repetido");
                alert.setContentText("Esse cliente já está cadastrado");

                alert.showAndWait();
            }
        }
    }

    private void limparCampos(){
        nomeCliente.clear();
        cpfCliente.clear();
        emailCliente.clear();
        telCliente.clear();
    }

    @FXML
    private void back() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        InicialController.inicial.close();
    }

}
