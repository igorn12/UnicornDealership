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
    private int cont = 0;

    private ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<Vendedor> cbVendedores;

    @FXML
    private Label labelAno, labelModelo, labelPlaca, labelDescricao, labelPreco, labelKm;

    @FXML
    private Label labelConfirm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alimentarCampos();
        refreshVendedores();
    }

    private void refreshVendedores(){
        vendedores.clear();
        vendedores.setAll(vendedorDAO.listNomeVendedor());
        cbVendedores.getItems().setAll(vendedores);
    }

    @FXML
    private void venderVeiculo() throws IOException {
        if(cbVendedores.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText("O campo com o nome do vendedor");
            alert.setContentText("INSIRA UM VENDEDOR");

            alert.showAndWait();
        }else{
            Vendas v = new Vendas (InicialController.veiculo.getIdVeiculo(), InicialController.veiculo.getValorVenda());
            v.setRendimento(InicialController.veiculo.getValorVenda());
            cont++;
            vendedorDAO.updateTotalVendas(cont, cbVendedores.getSelectionModel().getSelectedItem());
            vendasDAO.insertVendas(v);
            labelConfirm.setVisible(true);
        }
    }

    @FXML
    private void addVendedor() throws IOException {
        Parent addVendedor = FXMLLoader.load(getClass().getResource("/view/AddVendedorVendas.fxml"));
        Principal.principalStage.setTitle("Adicionar Vendedor");
        Principal.principalStage.setScene(new Scene(addVendedor));
    }

    @FXML
    private void voltar()throws IOException {
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
        Principal.principalStage.setScene(new Scene(voltar));
    }

    private void verificaCb() throws IOException {
        if(cbVendedores.getItems().isEmpty()){
            addVendedor();
        }
    }

    private void alimentarCampos(){
        labelModelo.setVisible(true);
        labelModelo.setText(InicialController.veiculo.getModelo());

        labelPlaca.setVisible(true);
        labelPlaca.setText(InicialController.veiculo.getPlaca());

        labelPreco.setVisible(true);
        String preco = String.valueOf(InicialController.veiculo.getValorVenda());
        labelPreco.setText(preco);

        labelAno.setVisible(true);
        String ano = String.valueOf(InicialController.veiculo.getAno());
        labelAno.setText(ano);

        labelKm.setVisible(true);
        String km = String.valueOf(InicialController.veiculo.getKmRodados());
        labelKm.setText(km);

        labelDescricao.setVisible(true);
        labelDescricao.setText(InicialController.veiculo.getDescricao());
    }

}
