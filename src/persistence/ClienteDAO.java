package persistence;

import model.Cliente;
import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    Conection con = new Conection();

    private final String INSERTCLIENTE = "INSERT INTO CLIENTE (NOME_CLIENTE, CPF_CLIENTE, EMAIL, TEL_CLIENTE) VALUES (?,?,?,?)";
    private final String LISTCLIENTE = "SELECT NOME_CLIENTE, CPF_CLIENTE, TEL_CLIENTE, EMAIL FROM CLIENTE";
    private final String LISTNOMECLIENTE = "SELECT NOME_CLIENTE FROM CLIENTE";

    public boolean insertCliente(Cliente c){
        try{
            con.conecta();

            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTCLIENTE);

            preparaInstrucao.setString(1,c.getNomeCliente());
            preparaInstrucao.setString(2, c.getCpfCliente());
            preparaInstrucao.setString(3, c.getEmailCliente());
            preparaInstrucao.setString(4, c.getTelCliente());

            con.desconecta();
            return true;
        }catch (SQLException sqle){
            return false;
        }
    }

    public ArrayList<Cliente> listCliente(){
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTCLIENTE);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("NOME_CLIENTE"), rs.getString("CPF_CLIENTE"),
                        rs.getString("TEL_CLIENTE"), rs.getString("EMAIL"));
                lista.add(c);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return lista;
    }

    public ArrayList<Cliente> listNomeCliente(){
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTCLIENTE);

            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("NOME_CLIENTE"));
                lista.add(c);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return lista;
    }
}


