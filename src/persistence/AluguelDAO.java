package persistence;

import model.Aluguel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AluguelDAO {
    Conection con = new Conection();

    private final String INSERTALUGUEL = "INSERT INTO ALUGUEL (ID_VEICULO, ID_CLIENTE, LOCATARIO, VALOR_ALUGUEL, DATA_ALUGUEL, DATA_DEVOLUCAO) VALUES (?,?,?,?,?,?)";
    private final String LISTALUGUEL = "SELECT ID_VEICULO, DATA_ALUGUEL, DATA_DEVOLUCAO, LOCATARIO FROM ALUGUEL";
    private final String VALIDAALUGUEL = "SELECT COUNT(LOCATARIO) FROM VENDEDOR WHERE UPPER(LOCATARIO) = ?";

    public boolean insertAluguel(Aluguel a){
        try{
            con.conecta();

            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTALUGUEL);

            preparaInstrucao.setInt(1, a.getIdVeiculo());
            preparaInstrucao.setInt(2, a.getIdCliente());
            preparaInstrucao.setString(3, a.getLocatario().toUpperCase());
            preparaInstrucao.setDouble(4, a.getValorAluguel());
            preparaInstrucao.setDate(5, a.getDtEntrada());
            preparaInstrucao.setDate(6, a.getDtEntrega());

            con.desconecta();
            return true;
        }catch (SQLException sqle){
            return false;
        }
    }

    public ArrayList<Aluguel> listAluguel(){
        ArrayList<Aluguel> lista = new ArrayList<>();
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTALUGUEL);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Aluguel a = new Aluguel(rs.getInt("ID_VEICULO"), rs.getInt("ID_CLIENTE"), rs.getString("LOCATARIO"),
                        rs.getDouble("VALOR_ALUGUEL"), rs.getDate("DATA_ALUGUEL"), rs.getDate("DATA_DEVOLUCAO"));
                lista.add(a);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return lista;
    }

    public boolean validaAluguel(String locatario){
        int qtd=0;
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(VALIDAALUGUEL);

            preparaInstrucao.setString(1, locatario.toUpperCase());

            ResultSet rs = preparaInstrucao.executeQuery();

            if(rs.next())
                qtd = rs.getInt(1);

            con.desconecta();
        } catch (SQLException e) {
        }
        return qtd == 0;
    }
}
