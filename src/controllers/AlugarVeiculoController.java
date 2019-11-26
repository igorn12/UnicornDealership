package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Principal;
import model.Aluguel;
import model.Cliente;
import persistence.AluguelDAO;
import persistence.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AlugarVeiculoController implements Initializable {
    private AluguelDAO aluguelDAO = new AluguelDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    public static Stage principal;

    @FXML
    private JFXTextField txPreco;

    @FXML
    private JFXComboBox<Cliente> cbClientes;

    @FXML
    private JFXDatePicker dtInicio, dtEntrega;

    @FXML
    private Label labelConfirm;


//    private void refreshClientes(){
//        clientes.clear();
//        clientes.addAll(clienteDAO.listNomeCliente());
//        cbClientes.getItems().setAll(clientes);
//    }

    @FXML
    private void addCliente() throws IOException {
        Parent addCliente = FXMLLoader.load(getClass().getResource("/view/AddCliente.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Adicionar Cliente");
        stage.setScene(new Scene(addCliente));
        stage.show();
        principal = stage;
    }

    @FXML
    private void back() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        principal.close();
    }

    @FXML
    private void addAluguel(){
        if(cbClientes.getSelectionModel().isEmpty() || dtInicio.getValue() == null || dtEntrega.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Campos em branco");
            alert.setContentText("Verifique se algum campo está em branco");
            alert.showAndWait();
        }else{
            String nome = cbClientes.getSelectionModel().getSelectedItem().getNomeCliente();
            Date dataEmprestimo = Date.valueOf(dtInicio.getValue());
            Date dataDevolucao = Date.valueOf(dtEntrega.getValue());
            int idVeiculo = InicialController.veiculo.getIdVeiculo();
            int idCliente = cbClientes.getSelectionModel().getSelectedItem().getIdCliente();
            double valorAluguel = Double.valueOf(txPreco.getText());

            Aluguel a = new Aluguel(idVeiculo, idCliente, nome, valorAluguel, dataEmprestimo, dataDevolucao);
            aluguelDAO.insertAluguel(a);
            aluguelDAO.validaAluguel(a);
            labelConfirm.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
////        String precoDoAluguel;
////        precoDoAluguel = String.valueOf(InicialController.veiculo.getValorAluguel());
////        txPreco.setText(precoDoAluguel);
//
//        refreshClientes();
//        if(cbClientes.getItems().isEmpty()){
//            try {
//                addCliente();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
    }
}
