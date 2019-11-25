package persistence;

import model.Vendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class VendedorDAO {
    private Conection con = new Conection();

    private final String INSERTVENDEDOR = "INSERT INTO VENDEDOR(NOME_VENDEDOR, CPF_VENDEDOR, TEL_VENDEDOR, SALARIO) VALUES (?,?,?,?)";
    private final String LISTVENDEDOR = "SELECT NOME_VENDEDOR, CPF_VENDEDOR, TEL_VENDEDOR, SALARIO, TOTAL_VENDAS FROM VENDEDOR";
    private final String UPDATESALARIOVENDEDOR = "UPDATE VENDEDOR SET SALARIO = ? WHERE SALARIO = ?";
    private final String UPDATECPFVENDEDOR = "UPDATE VENDEDOR SET CPF_VENDEDOR = ? WHERE CPF_VENDEDOR = ?";
    private final String DELETEVENDEDOR = "DELETE FROM VENDEDOR WHERE NOME_VENDEDOR = ?";
    private final String LISTVENDEDORNOME = "SELECT NOME_VENDEDOR FROM VENDEDOR";


    public boolean insertVendedor(Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(INSERTVENDEDOR);

            preparaInstrucao.setString(1, v.getNomeVendedor().toUpperCase());
            preparaInstrucao.setString(2, v.getCpfVendedor().toUpperCase());
            preparaInstrucao.setString(3, v.getTelefone().toUpperCase());
            preparaInstrucao.setDouble(4, v.getSalario());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;

        }
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

    public ArrayList<Vendedor> listVendedor() {
        ArrayList<Vendedor> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVENDEDOR);


            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Vendedor v = new Vendedor(rs.getString("NOME_VENDEDOR"), rs.getString("CPF_VENDEDOR"),
                        rs.getString("TEL_VENDEDOR"), rs.getDouble("SALARIO"), rs.getInt("TOTAL_VENDAS"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        Collections.sort(lista);
        return lista;
    }

    public ArrayList<Vendedor> listVendedorNome() {
        ArrayList<Vendedor> lista = new ArrayList<>();

        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(LISTVENDEDORNOME);


            ResultSet rs = preparaInstrucao.executeQuery();

            while (rs.next()) {
                Vendedor v = new Vendedor(rs.getString("NOME_VENDEDOR"));
                lista.add(v);
            }
            con.desconecta();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        Collections.sort(lista);
        return lista;
    }


}
