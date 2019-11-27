package controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Principal;
import model.Veiculo;
import persistence.VeiculoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicialController implements Initializable {
    public static Veiculo veiculo;
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private ObservableList<Veiculo> veiculos = FXCollections.observableArrayList();

    @FXML
    public static Stage inicial;

    @FXML
    private TableView<Veiculo> tableInicio;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, Double> colunaAluguel;

    @FXML
    private TableColumn<Veiculo, Double> colunaVenda;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAno;

    @FXML
    private TableColumn<Veiculo, Double> colunaKm;

    @FXML
    private TableColumn<Veiculo, String> colunaDescricao;


    @FXML
    private void register() throws IOException{
        Parent register = FXMLLoader.load(getClass().getResource("/view/Registrar.fxml"));
        Principal.principalStage.setTitle("Registrar Veículo");
        Principal.principalStage.setScene(new Scene(register));
    }
    @FXML
    private void toManage() throws IOException {
        Parent remove = FXMLLoader.load(getClass().getResource("/view/Gerenciar.fxml"));
        Principal.principalStage.setScene(new Scene(remove));
    }
    @FXML
    private void sellers() throws IOException{
        Parent sell = FXMLLoader.load(getClass().getResource("/view/Vendedores.fxml"));
        Principal.principalStage.setTitle("Vendedores");
        Principal.principalStage.setScene(new Scene(sell));
    }
    @FXML
    private void rentals() throws IOException{
        Parent rentals = FXMLLoader.load(getClass().getResource("/view/Alugueis.fxml"));
        Principal.principalStage.setTitle("Aluguéis");
        Principal.principalStage.setScene(new Scene(rentals));
    }

    @FXML
    private void rentVeiculo() throws IOException {
        if(tableInicio.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Veiculo não selecionado");
            alert.setContentText("Escolha um veiculo para alugar");
            alert.showAndWait();
        } else {
            veiculo = tableInicio.getSelectionModel().getSelectedItem();
            Parent alugarVeiculo = FXMLLoader.load(getClass().getResource("/view/AlugarVeiculo.fxml"));
            Principal.principalStage.setTitle("Adicionar Aluguel");
            Principal.principalStage.setScene(new Scene(alugarVeiculo));
        }
    }

    @FXML
    private void sellVeiculo() throws IOException {
        if(tableInicio.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Veiculo não selecionado");
            alert.setContentText("Escolha um veiculo para vender");
            alert.showAndWait();
        } else {
            veiculo = tableInicio.getSelectionModel().getSelectedItem();
            Parent venderVeiculo = FXMLLoader.load(getClass().getResource("/view/AddVenda.fxml"));
            Principal.principalStage.setTitle("Vender Veiculo");
            Principal.principalStage.setScene(new Scene(venderVeiculo));
        }
    }

    @FXML
    private void addCliente()throws IOException{
            Parent addCliente = FXMLLoader.load(getClass().getResource("/view/AddCliente.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adicionar Cliente");
            stage.setScene(new Scene(addCliente));
            stage.show();
            inicial = stage;
    }

    @FXML
    private void out(){
        Principal.principalStage.close();
    }

    private void refreshVeiculos(){
        veiculos.clear();
        veiculos.addAll(veiculoDAO.listVeiculo());
        tableInicio.setItems(veiculos);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshVeiculos();

        colunaModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("modelo"));
        colunaAluguel.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorAluguel"));
        colunaVenda.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorVenda"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("ano"));
        colunaKm.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("kmRodados"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("descricao"));
    }
}
