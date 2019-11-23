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
import main.Principal;
import model.Veiculo;
import persistence.VeiculoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicialController implements Initializable {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private ObservableList<Veiculo> automoveis = FXCollections.observableArrayList();

    @FXML
    private TableView<Veiculo> veiculos;

    @FXML
    private TableColumn<Veiculo, Integer> colunaIdVeiculo;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAno;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    @FXML
    private TableColumn<Veiculo, Double> colunaVenda;

    @FXML
    private TableColumn<Veiculo, Double> colunaAluguel;

    @FXML
    private TableColumn<Veiculo, String> colunaTipo;

    @FXML
    private TableColumn<Veiculo, Double> colunaKm;

    @FXML
    private void register() throws IOException{
        Parent register = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        Principal.principalStage.setScene(new Scene(register));
    }
    @FXML
    private void toManage() throws IOException {
        Parent remove = FXMLLoader.load(getClass().getResource("/view/ToManage.fxml"));
        Principal.principalStage.setScene(new Scene(remove));
    }
    @FXML
    private void sellers() throws IOException{
        Parent sell = FXMLLoader.load(getClass().getResource("/view/Sellers.fxml"));
        Principal.principalStage.setScene(new Scene(sell));
    }
    @FXML
    private void yield() throws IOException{
        Parent yield = FXMLLoader.load(getClass().getResource("/view/Yield.fxml"));
        Principal.principalStage.setScene(new Scene(yield));
    }
    @FXML
    private void out(){
        Principal.principalStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        automoveis.addAll(veiculoDAO.listVeiculo());
        veiculos.setItems(automoveis);

        colunaIdVeiculo.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("idVeiculo"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("ano"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("modelo"));
        colunaPlaca.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("placa"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("tipo"));
        colunaAluguel.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorAluguel"));
        colunaVenda.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorVenda"));
        colunaKm.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("kmRodados"));
    }
}
