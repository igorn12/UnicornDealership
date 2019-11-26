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
import model.Vendedor;
import persistence.VendedorDAO;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VendedoresController implements Initializable {
    public static Vendedor v;

    private VendedorDAO vendedorDAO = new VendedorDAO();

    private ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();

    @FXML
    private TableView<Vendedor> tableVendedores;

    @FXML
    private TableColumn<Vendedor, String> colunaNomeVendedor;

    @FXML
    private TableColumn<Vendedor, String> colunaCpfVendedor;

    @FXML
    private TableColumn<Vendedor, String> colunaTelVendedor;

    @FXML
    private TableColumn<Vendedor, Double> colunaSalarioVendedor;

    @FXML
    private TableColumn<Vendedor, Integer> colunaTotalVendas;

    @FXML
    public static Stage vendedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNomeVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nomeVendedor"));
        colunaCpfVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("cpfVendedor"));
        colunaTelVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("telefone"));
        colunaSalarioVendedor.setCellValueFactory(new PropertyValueFactory<Vendedor, Double>("salario"));
        colunaTotalVendas.setCellValueFactory(new PropertyValueFactory<Vendedor, Integer>("totalVendas"));

        refreshVendedores();
    }

    private void refreshVendedores() {
        vendedores.clear();
        vendedores.addAll(vendedorDAO.listVendedor());
        tableVendedores.setItems(vendedores);
    }

    @FXML
    private void addSeller() throws Exception{
         Parent addSeller = FXMLLoader.load(getClass().getResource("/view/AddVendedor.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(addSeller));
         stage.setTitle("Adicionar Vendedor");
         stage.show();
         vendedor = stage;
         refreshVendedores();
    }

    @FXML
    private void attSeller() throws Exception{
        if(tableVendedores.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Vendedor não selecionado");
            alert.setContentText("Escolha um Vendedor para remover");
            alert.showAndWait();
        } else {
            Parent attSeller = FXMLLoader.load(getClass().getResource("/view/AttVendedor.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(attSeller));
            stage.setTitle("Atualizar Vendedor");
            stage.show();
            vendedor = stage;
            v = tableVendedores.getSelectionModel().getSelectedItem();
        }
        refreshVendedores();
    }

    @FXML
    private void voltar() throws IOException{
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    @FXML
    private void deleteSeller(){
        if(tableVendedores.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("Vendedor não selecionado");
            alert.setContentText("Escolha um Vendedor para remover");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Confirmar exclusão de cadastro");
            alert.setContentText("Tem certeza que deseja excluir o cadastro?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                vendedorDAO.deleteVendedor(tableVendedores.getSelectionModel().getSelectedItem());
                int indice = tableVendedores.getSelectionModel().getSelectedIndex();
                tableVendedores.getItems().remove(indice);
            }
        }
        refreshVendedores();
    }
}
