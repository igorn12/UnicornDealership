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
    private final String UPDATENOMEVENDEDOR = "UPDATE VENDEDOR SET NOME_VENDEDOR = ? WHERE NOME_VENDEDOR = ?";
    private final String UPDATETELVENDEDOR = "UPDATE VENDEDOR SET TEL_VENDEDOR = ? WHERE TEL_VENDEDOR = ?";
    private final String UPDATETOTALVENDAS = "UPDATE VENDEDOR SET TOTAL_VENDDAS = ? WHERE TOTAL_VENDAS = ?";
    private final String DELETEVENDEDOR = "DELETE FROM VENDEDOR WHERE NOME_VENDEDOR = ?";
    private final String VALIDAVENDEDOR = "SELECT COUNT(NOME_VENDEDOR) FROM VENDEDOR WHERE UPPER(NOME_VENDEDOR) = ?";
    private final String VALIDACPFVENDEDOR = "SELECT COUNT(CPF_VENDEDOR) FROM VENDEDOR WHERE UPPER(CPF_VENDEDOR) = ?";



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

    public boolean updateNomeVendedor(String nome, Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATENOMEVENDEDOR);

            preparaInstrucao.setString(1, nome.toUpperCase());
            preparaInstrucao.setString(2, v.getNomeVendedor());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public boolean updateSalarioVendedor(double salario, Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATESALARIOVENDEDOR);

            preparaInstrucao.setDouble(1, salario);
            preparaInstrucao.setDouble(2, v.getSalario());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateTelVendedor(String telefone, Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATETELVENDEDOR);

            preparaInstrucao.setString(1, telefone.toUpperCase());
            preparaInstrucao.setString(2, v.getTelefone());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public boolean updateTotalVendas(int totalVendas, Vendedor v){
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(UPDATETOTALVENDAS);

            preparaInstrucao.setInt(1, totalVendas);
            preparaInstrucao.setInt(2, v.getTotalVendas());

            preparaInstrucao.execute();

            con.desconecta();

            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    public boolean validaVendedor(String nomeVendedor) {
        int qtd=0;
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(VALIDAVENDEDOR);

            preparaInstrucao.setString(1, nomeVendedor.toUpperCase());

            ResultSet rs = preparaInstrucao.executeQuery();

            if(rs.next())
                qtd = rs.getInt(1);

            con.desconecta();
        } catch (SQLException e) {
        }
        return qtd == 0;
    }

    public boolean validaCpfVendedor(String cpf){
        int qtd=0;
        try {
            con.conecta();
            PreparedStatement preparaInstrucao;
            preparaInstrucao = con.getConexao().prepareStatement(VALIDACPFVENDEDOR);

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
