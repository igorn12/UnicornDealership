package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Principal;
import model.Veiculo;
import persistence.VeiculoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ToManageController implements Initializable {
    public static Veiculo veiculo;
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    private ObservableList<Veiculo> veiculos = FXCollections.observableArrayList();

    @FXML
    private TableView<Veiculo> tableVeiculos;

    @FXML
    private TableColumn<Veiculo, Integer> colunaIdVeiculo;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, String> colunaPlaca;

    @FXML
    private TableColumn<Veiculo, Double> colunaAluguel;

    @FXML
    private TableColumn<Veiculo, Double> colunaVenda;

    @FXML
    private TableColumn<Veiculo, Double> colunaKm;

    @FXML
    private TableColumn<Veiculo, String> colunaTipo;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAnoVeiculo;

    @FXML
    public static Stage principal;

    @FXML
    private void voltar() throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    @FXML
    private void deleteVeiculo(){
        if(tableVeiculos.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Veículo não selecionado");
            alert.setContentText("Escolha um Veículo para remover");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Confirmar exclusão de veículo");
            alert.setContentText("Tem certeza que deseja excluir o cadastro?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                veiculoDAO.deleteVeiculo(tableVeiculos.getSelectionModel().getSelectedItem());
                int indice = tableVeiculos.getSelectionModel().getSelectedIndex();
                tableVeiculos.getItems().remove(indice);
            }
        }
    }

    @FXML
    private void attVeiculo() throws IOException {
        if(tableVeiculos.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Veiculo não selecionado");
            alert.setContentText("Escolha um Veiculo para atualizar");
            alert.showAndWait();
        } else {
            Parent attVeiculo = FXMLLoader.load(getClass().getResource("/view/AttVeiculo.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(attVeiculo));
            stage.setTitle("Atualizar Veículo");
            stage.show();
            principal = stage;
            veiculo = tableVeiculos.getSelectionModel().getSelectedItem();
        }
        refreshVeiculos();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshVeiculos();

        colunaIdVeiculo.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("idVeiculo"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("modelo"));
        colunaPlaca.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("placa"));
        colunaAluguel.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorAluguel"));
        colunaVenda.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorVenda"));
        colunaKm.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("kmRodados"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("tipo"));
        colunaAnoVeiculo.setCellValueFactory(new PropertyValueFactory<Veiculo, Integer>("ano"));
    }

    private void refreshVeiculos(){
        veiculos.clear();
        veiculos.addAll(veiculoDAO.listVeiculo());
        tableVeiculos.setItems(veiculos);
        tableVeiculos.getSortOrder().add(colunaIdVeiculo);
    }
}
