package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class RentController implements Initializable {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private ObservableList<Veiculo> veiculos = FXCollections.observableArrayList();

    @FXML
    private TableView<Veiculo> tableVeiculo;

    @FXML
    private TableColumn<Veiculo, Integer> colunaIdVeiculo;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, Double> colunaKm;

    @FXML
    private TableColumn<Veiculo, Double> colunaAluguel;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAno;

    @FXML
    private TableColumn<Veiculo, String> colunaDescricao;

    @FXML
    public static Stage principal;

    @FXML
    private void voltar()throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }
    @FXML
    private void rentVeiculo() throws IOException {
        Parent attSeller = FXMLLoader.load(getClass().getResource("/view/AttSeller.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(attSeller));
        stage.setTitle("Atualizar Vendedor");
        stage.show();
        principal = stage;
        refreshAluguel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshAluguel();

        colunaIdVeiculo.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("idVeiculo"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("modelo"));
        colunaKm.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("kmRodados"));
        colunaAluguel.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorAluguel"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("ano"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("descricao"));
    }

    private void refreshAluguel(){
        veiculos.clear();
        veiculos.addAll(veiculoDAO.listVeiculo());
        tableVeiculo.setItems(veiculos);
        tableVeiculo.getSortOrder().add(colunaIdVeiculo);
    }
}
