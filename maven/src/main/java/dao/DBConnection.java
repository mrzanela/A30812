package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sâmeck
 */
public class DBConnection {

    private static DBConnection instance = null;
    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "marketplace";
    private String driver = "com.mysql.jdbc.Driver";
    private String driverManager = null;

    private DBConnection() {
        try {
            conn = DriverManager.getConnection(url + dbName, "root", "root");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url + dbName, "root", "root");
            }
        } catch (SQLException e) {
            // Tratar a exceção de forma apropriada
            System.err.println("Erro ao obter conexão com o banco de dados:");
            e.printStackTrace();
        }
        return conn;
    }
    
    
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Tratar a exceção de forma apropriada
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }
    }
}
