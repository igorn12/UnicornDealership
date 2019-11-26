package persistence;

import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    Conection con = new Conection();

    private final String INSERTVEICULO = "INSERT INTO VEICULO(MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO, DESCRICAO, ANO) VALUES (?,?,?,?,?,?,?,?)";
    private final String LISTVEICULOS = "SELECT ID_VEICULO, MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO, DESCRICAO, ANO FROM VEICULO";
    private final String DELETEVEICULO = "DELETE FROM VEICULO WHERE MODELO = ?";
    private final String UPDATEMODELO = "UPDATE VEICULO SET MODELO = ? WHERE MODELO = ?";
    private final String UPDATEPLACA = "UPDATE VEICULO SET PLACA = ? WHERE PLACA = ?";
    private final String UPDATEVENDA = "UPDATE VEICULO SET VENDA = ? WHERE VENDA = ?";
    private final String UPDATEALUGUEL = "UPDATE VEICULO SET ALUGUEL = ? WHERE ALUGUEL = ?";

    public boolean insertVeiculo(Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVEICULO);
            preparaInstrucao.setString(1, v.getModelo().toUpperCase());
            preparaInstrucao.setString(2, v.getPlaca().toUpperCase());
            preparaInstrucao.setDouble(3, v.getValorAluguel());
            preparaInstrucao.setDouble(4, v.getValorVenda());
            preparaInstrucao.setDouble(5, v.getKmRodados());
            preparaInstrucao.setString(6, v.getTipo().toUpperCase());
            preparaInstrucao.setString(7, v.getDescricao().toUpperCase());
            preparaInstrucao.setInt(8, v.getAno());
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
                        rs.getString("MODELO"), rs.getString("PLACA"), rs.getString("DESCRICAO"), rs.getDouble("KM_RODADOS"),
                        rs.getDouble("VENDA"), rs.getDouble("ALUGUEL"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return lista;
    }

    public boolean updateModelo(String modelo, Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATEMODELO);
            preparaInstrucao.setString(1, modelo.toUpperCase());
            preparaInstrucao.setString(2, v.getModelo());
            preparaInstrucao.execute();
            con.desconecta();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updatePlaca(String placa, Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATEPLACA);
            preparaInstrucao.setString(1, placa.toUpperCase());
            preparaInstrucao.setString(2, v.getPlaca());
            preparaInstrucao.execute();
            con.desconecta();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateVenda(double venda, Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATEVENDA);
            preparaInstrucao.setDouble(1, venda);
            preparaInstrucao.setDouble(2, v.getValorVenda());
            preparaInstrucao.execute();
            con.desconecta();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateAluguel(double aluguel, Veiculo v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATEALUGUEL);
            preparaInstrucao.setDouble(1, aluguel);
            preparaInstrucao.setDouble(2, v.getValorAluguel());
            preparaInstrucao.execute();
            con.desconecta();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
