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
import javafx.util.StringConverter;
import main.Principal;
import model.Aluguel;
import model.Cliente;
import persistence.AluguelDAO;
import persistence.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AlugarVeiculoController implements Initializable {
    private AluguelDAO aluguelDAO = new AluguelDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<Cliente> cbClientes;

    @FXML
    private JFXTextField txPreco;

    @FXML
    private JFXDatePicker dtInicio, dtEntrega;

    @FXML
    private Label labelConfirm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alimentarCampos();
        refreshClientes();

        dtEntrega.setConverter(data());
        dtInicio.setConverter(data());
    }

    private StringConverter<LocalDate> data() {
        String pattern = "dd/MM/yyyy";
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        return converter;
    }

    @FXML
    private void addCliente() throws IOException {
        Parent addCliente = FXMLLoader.load(getClass().getResource("/view/AddClienteAluguel.fxml"));
        Principal.principalStage.setTitle("Adicionar Cliente");
        Principal.principalStage.setScene(new Scene(addCliente));
    }

    @FXML
    private void voltar() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
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
            aluguelDAO.validaAluguel(a.getLocatario());
            labelConfirm.setVisible(true);
        }
    }
    private void refreshClientes(){
        clientes.clear();
        clientes.setAll(clienteDAO.listCliente());
        cbClientes.getItems().setAll(clientes);
    }

    private void alimentarCampos(){
        String preco = String.valueOf(InicialController.veiculo.getValorAluguel());
        txPreco.setText(preco);
    }

}
