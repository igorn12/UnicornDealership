package persistence;

import model.Vendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VendedorDAO {
    private Conection con = new Conection();

    private final String INSERTVENDEDOR = "INSERT INTO VENDEDOR(NOME_VENDEDOR, CPF_VENDEDOR, TEL_VENDEDOR, SALARIO) VALUES (?,?,?,?)";
    private final String UPDATEVENDEDOR = "UPDATE VENDEDOR SET CPF_VENDEDOR = OR NOME_VENDEDOR  = ? WHERE CPF_VENDEDOR = ? ";
    private final String DELETEVENDEDOR = "DELETE FROM VENDEDOR WHERE NOME_VENDEDOR = ?";
    private final String LISTVENDEDOR = "SELECT * FROM VENDEDOR";


    public boolean insertVendedor(String nome, String cpf, String telefone, double salario){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVENDEDOR);

            preparaInstrucao.setString(1, nome.toUpperCase());
            preparaInstrucao.setString(2, cpf.toUpperCase());
            preparaInstrucao.setString(3, telefone.toUpperCase());
            preparaInstrucao.setDouble(4, salario);

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;

        }
    }
    public ArrayList<Vendedor> listVendedor() {
        ArrayList<Vendedor> lista = new ArrayList<>();

        try {
            con.conecta();
            Statement preparaInstrucao;
            preparaInstrucao = con.getConexao().createStatement();


            ResultSet rs = preparaInstrucao.executeQuery(LISTVENDEDOR);

            while (rs.next()) {
                Vendedor v = new Vendedor(rs.getString("NOME_VENDEDOR"), rs.getString("CPF_VENDEDOR"), rs.getString("TEL_VENDEDOR"), rs.getDouble("SALARIO"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        Collections.sort(lista);
        return lista;
    }

    public boolean deleteVendedor(Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(DELETEVENDEDOR);

            preparaInstrucao.setString(1, v.getNomeVendedor());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }
    }
}
