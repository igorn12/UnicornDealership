package persistence;

import model.Aluguel;
import model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AluguelDAO {
    Conection con = new Conection();

    private final String INSERTALUGUEL = "INSERT INTO ALUGUEL (ID_VEICULO, ID_CLIENTE, DATA_ALUGUEL, DATA_DEVOLUCAO, LOCATARIO) VALUES (?,?,?,?,?)";
    private final String LISTALUGUEL = "SELECT ID_VEICULO, DATA_ALUGUEL, DATA_DEVOLUCAO, LOCATARIO FROM ALUGUEL WHERE ALUGUEL.ID_VEICULO = VEICULO.ID_VEICULO";


    public boolean insertAluguel(Aluguel a){
        try{
            con.conecta();

            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTALUGUEL);

            preparaInstrucao.setInt(1, a.getIdVeiculo());
            preparaInstrucao.setInt(2, a.getIdCliente());
            preparaInstrucao.setString(3, a.getDataEntrada());
            preparaInstrucao.setString(4, a.getDataDevolucao());

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
                Aluguel a = new Aluguel(rs.getInt("ID_VEICULO"), rs.getInt("ID_CLIENTE"), rs.getString("DATA_ALUGUEL"),
                        rs.getString("DATA_DEVOLUCAO"), rs.getString("LOCATARIO"));
                lista.add(a);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return lista;
    }
}
