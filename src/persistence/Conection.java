package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    private static final String USUARIO = "admin";
    private static final String SENHA = "admin";
    private static final String CAMINHO = "jdbc:h2:~/IdeaProjects/UnicornDealership/lib";
    private static final String DRIVER = "org.h2.Driver";
    private Connection conection;

    public void conecta() {
        try {
            Class.forName(DRIVER);
            conection = DriverManager.getConnection(CAMINHO, USUARIO,SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    public void desconecta() {
        try {
            conection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public Connection getConexao() {
        return conection;
    }
}
