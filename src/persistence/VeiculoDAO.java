package persistence;

import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    Conection con = new Conection();

    private final String INSERTVEICULO = "INSERT INTO VEICULO(ANO, MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO, DESCRICAO) VALUES (?,?,?,?,?,?,?,?)";
    private final String LISTVEICULOS = "SELECT ID_VEICULO, ANO, MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO FROM VEICULO";
    private final String LISTVEICULOSMODELO = "SELECT ID_VEICULO, ANO, MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO FROM VEICULO";
    private final String DELETEVEICULO = "DELETE FROM VEICULO WHERE MODELO = ?";

    public boolean insertVeiculo(int ano,String modelo,String placa,double aluguel,double venda,double kms,String tipo,String descricao){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVEICULO);

            preparaInstrucao.setInt(1, ano);
            preparaInstrucao.setString(2, modelo.toUpperCase());
            preparaInstrucao.setString(3, placa.toUpperCase());
            preparaInstrucao.setDouble(4, aluguel);
            preparaInstrucao.setDouble(5, venda);
            preparaInstrucao.setDouble(6, kms);
            preparaInstrucao.setString(7, tipo.toUpperCase());
            preparaInstrucao.setString(8, descricao.toUpperCase());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;

        }
    }

    public boolean deleteVeiculo(Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(DELETEVEICULO);

            preparaInstrucao.setString(1, v.getModelo());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Veiculo> listVeiculo() {
        ArrayList<Veiculo> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVEICULOS);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo(rs.getInt("ID_VEICULO"), rs.getInt("ANO"),rs.getString("TIPO"),
                        rs.getString("MODELO"), rs.getString("PLACA"), rs.getDouble("KM_RODADOS"),
                        rs.getDouble("VENDA"), rs.getDouble("ALUGUEL"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return lista;
    }

    public ArrayList<Veiculo> listVeiculoId(int idVeiculo){
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVEICULOS);

            preparaInstrucao.setInt(1, idVeiculo);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo(rs.getInt("ID_VEICULO"), rs.getInt("ANO"),rs.getString("TIPO"),
                        rs.getString("MODELO"), rs.getString("PLACA"), rs.getDouble("KM_RODADOS"),
                        rs.getDouble("VENDA"), rs.getDouble("ALUGUEL"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return lista;
    }
    public ArrayList<Veiculo> listVeiculoModelo(String modelo){
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVEICULOSMODELO);

            preparaInstrucao.setString(1, modelo);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo(rs.getInt("ID_VEICULO"), rs.getInt("ANO"),rs.getString("TIPO"),
                        rs.getString("MODELO"), rs.getString("PLACA"), rs.getDouble("KM_RODADOS"),
                        rs.getDouble("VENDA"), rs.getDouble("ALUGUEL"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return lista;
    }
}
