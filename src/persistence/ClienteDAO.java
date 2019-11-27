package persistence;
import model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class ClienteDAO {
    Conection con = new Conection();

    private final String INSERTCLIENTE = "INSERT INTO CLIENTE (NOME_CLIENTE, CPF_CLIENTE, EMAIL, TEL_CLIENTE) VALUES (?,?,?,?)";
    private final String LISTNOMECLIENTE = "SELECT NOME_CLIENTE FROM CLIENTE";
    private final String VALIDACLIENTE = "SELECT COUNT(CPF_CLIENTE) FROM CLIENTE WHERE UPPER(CPF_CLIENTE) = ?";

    public boolean insertCliente(Cliente c){
        try{
            con.conecta();

            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTCLIENTE);

            preparaInstrucao.setString(1, c.getNomeCliente().toUpperCase());
            preparaInstrucao.setString(2, c.getCpfCliente().toUpperCase());
            preparaInstrucao.setString(3, c.getEmailCliente().toUpperCase());
            preparaInstrucao.setString(4, c.getTelCliente().toUpperCase());

            con.desconecta();
            return true;
        }catch (SQLException sqle){
            return false;
        }
    }

    public ArrayList<Cliente> listNomeCliente(){
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTNOMECLIENTE);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("NOME_CLIENTE"));
                lista.add(c);
            }

            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        Collections.sort(lista);
        return lista;
    }

    public boolean validaCpfCliente(String cpf){
        int qtd=0;
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(VALIDACLIENTE);

            preparaInstrucao.setString(1, cpf.toUpperCase());

            ResultSet rs = preparaInstrucao.executeQuery();

            if(rs.next())
                qtd = rs.getInt(1);

            con.desconecta();
        } catch (SQLException e) {
        }
        return qtd == 0;
    }
}


