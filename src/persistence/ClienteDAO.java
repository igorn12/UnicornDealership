package persistence;

import model.Cliente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    Conection con = new Conection();

    private final String INSERTCLIENTE = "INSERT INTO CLIENTE (NOME_CLIENTE, CPF_CLIENTE, EMAIL, TEL_CLIENTE) VALUES (?,?,?,?)";

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
}
