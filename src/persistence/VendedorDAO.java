package persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendedorDAO {
    private Conection con = new Conection();

    private final String INSERTVENDEDOR = "INSERT INTO VENDEDOR(SALARIO, NOME_VENDEDOR, CPF_VENDEDOR, TEL_VENDEDOR) VALUES (?,?,?,?)";
    private final String UPDATEVENDEDOR = "UPDATE VENDEDOR SET CPF_VENDEDOR = ? AND NOME_VENDEDOR  = ? WHERE CPF_VENDEDOR = ? ";
    private final String DELETEVENDEDOR = "DELETE FROM VENDEDOR WHERE NOME_VENDEDOR = ? OR CPF_VENDEDOR = ?";
    private final String LISTVENDEDOR = "SELECT * FROM VENDEDOR";


    public boolean insertVendedor(double salario, String nome, String cpf, String telefone){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVENDEDOR);

            preparaInstrucao.setDouble(1, salario);
            preparaInstrucao.setString(2, nome.toUpperCase());
            preparaInstrucao.setString(3, cpf.toUpperCase());
            preparaInstrucao.setString(4, telefone.toUpperCase());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;

        }
    }
}
