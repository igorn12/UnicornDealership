package controllers;

import com.jfoenix.controls.JFXComboBox;
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
import model.Vendas;
import model.Vendedor;
import persistence.VendasDAO;
import persistence.VendedorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddVendaController implements Initializable {
    private VendasDAO vendasDAO = new VendasDAO();
    private VendedorDAO vendedorDAO = new VendedorDAO();

    @FXML
    public static Stage principal;

    private ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<Vendedor> cbVendedores;

    @FXML
    private Label labelAno, labelModelo, labelPlaca, labelDescricao, labelPreco, labelKm;

    private void refreshVendedores(){
        vendedores.clear();
        vendedores.setAll(vendedorDAO.listNomeVendedor());
        cbVendedores.getItems().setAll(vendedores);
    }

    @FXML
    private void venderVeiculo(){
        if(cbVendedores.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("O campo com o nome do vendedor");
            alert.setContentText("INSIRA UM VENDEDOR");

            alert.showAndWait();
        }else{
            Vendas v = new Vendas (InicialController.veiculo.getIdVeiculo(), InicialController.veiculo.getValorVenda());
            v.setRendimento(InicialController.veiculo.getValorVenda());
            vendasDAO.insertVendas(v);
        }
    }

    @FXML
    private void addVendedor() throws IOException{
        Parent addVendedor = FXMLLoader.load(getClass().getResource("/view/AddVendedor.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Adicionar Vendedor");
        stage.setScene(new Scene(addVendedor));
        stage.show();
        principal = stage;
    }

    @FXML
    private void back()throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
        principal.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*String preco = String.valueOf(InicialController.veiculo.getValorVenda());
        String ano = String.valueOf(InicialController.veiculo.getAno());
        String km = String.valueOf(InicialController.veiculo.getKmRodados());

        labelModelo.setText(InicialController.veiculo.getModelo());
        labelPlaca.setText(InicialController.veiculo.getPlaca());
        labelPreco.setText(preco);
        labelAno.setText(ano);
        labelKm.setText(km);
        labelDescricao.setText(InicialController.veiculo.getDescricao());
        */
        refreshVendedores();
        if(cbVendedores.getSelectionModel().isEmpty()){
            try {
                addVendedor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
