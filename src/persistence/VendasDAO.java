package persistence;


import model.Vendas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendasDAO {
    Conection con = new Conection();

    private final String INSERTVENDAS = "INSERT INTO VENDAS (ID_VEICULO, RENDIMENTO) VALUES (?,?)";
    private final String LISTRENDIMENTO = "SELECT RENDIMENTO FROM VENDAS";
    private final String LISTVENDAS = "SELECT ID_VENDAS, ID_VEICULO, RENDIMENTO FROM VENDAS WHERE VENDAS.ID_VEICULO = VEICULO.ID_VEICULO";
    private final String UPDATEVENDAS = "UPDATE VENDAS SET RENDIMENTO WHERE RENDIMENTO = ?";

    public boolean insertVendas(Vendas v){
        try{
            con.conecta();


            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVENDAS);

            preparaInstrucao.setInt(1, v.getIdVeiculo());
            preparaInstrucao.setDouble(2, v.getRendimento());

            preparaInstrucao.execute();


            con.desconecta();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public ArrayList<Vendas> listVendas() {
        ArrayList<Vendas> lista = new ArrayList<>();
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVENDAS);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Vendas v = new Vendas(rs.getInt("ID_VENDAS"), rs.getInt("ID_VEICULO"), rs.getDouble("RENDIMENTO"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
