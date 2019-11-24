package persistence;

import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    Conection con = new Conection();

    private final String INSERTVEICULO = "INSERT INTO VEICULO(MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO, DESCRICAO, ANO) VALUES (?,?,?,?,?,?,?,?)";
    private final String LISTVEICULOS = "SELECT ID_VEICULO, MODELO, PLACA, ALUGUEL, VENDA, KM_RODADOS, TIPO, ANO FROM VEICULO";
    private final String DELETEVEICULO = "DELETE FROM VEICULO WHERE MODELO = ?";

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
