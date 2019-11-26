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
import model.Aluguel;
import model.Veiculo;
import persistence.AluguelDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlugueisController implements Initializable {
    private AluguelDAO aluguelDAO = new AluguelDAO();

    private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();

    @FXML
    private TableView<Aluguel> tableAluguel;

    @FXML
    private TableColumn<Aluguel, Integer> colunaIdAluguel;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Aluguel, String> colunaLocatario;

    @FXML
    private TableColumn<Veiculo, Double> colunaAluguel;

    @FXML
    private TableColumn<Aluguel, String> colunaDtAluguel;

    @FXML
    private TableColumn<Aluguel, String> colunaDtDevolucao;

    @FXML
    private void voltar()throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshAluguel();

        colunaIdAluguel.setCellValueFactory(new PropertyValueFactory<Aluguel, Integer>("idAluguel"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<Veiculo, String>("modelo"));
        colunaLocatario.setCellValueFactory(new PropertyValueFactory<Aluguel, String>("locatario"));
        colunaAluguel.setCellValueFactory(new PropertyValueFactory<Veiculo, Double>("valorAluguel"));
        colunaDtAluguel.setCellValueFactory(new PropertyValueFactory<Aluguel, String>("dataEntrada"));
        colunaDtDevolucao.setCellValueFactory(new PropertyValueFactory<Aluguel, String>("dataDevolucao"));
        tableAluguel.getSortOrder().add(colunaIdAluguel);


    }

    private void refreshAluguel(){
        alugueis.clear();
        alugueis.addAll(aluguelDAO.listAluguel());
        tableAluguel.setItems(alugueis);
        tableAluguel.getSortOrder().add(colunaIdAluguel);
    }
}
