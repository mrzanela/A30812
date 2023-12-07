package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados.
 * Utiliza o padrão Singleton para garantir uma única instância da conexão
 * durante a execução do programa.
 * É utilizada pelas classes ClienteDAO, VendedorDAO, ProdutoDAO e
 * AppController.
 * 
 * @author Sâmeck
 */
public class DBConnection {

    private static DBConnection instance = null;
    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "marketplace";

    /**
     * Construtor privado para impedir a criação de instâncias externas.
     * Inicia a conexão com o banco de dados.
     */
    private DBConnection() {
        try {
            conn = DriverManager.getConnection(url + dbName, "root", "root");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }

    /**
     * Obtém a instância única da classe DBConnection (Singleton).
     *
     * @return A instância única da classe DBConnection.
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Obtém a conexão com o banco de dados.
     *
     * @return A conexão com o banco de dados.
     */
    public Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url + dbName, "root", "root");
            }
        } catch (SQLException e) {
            // Trata a exceção de forma apropriada
            System.err.println("Erro ao obter conexão com o banco de dados:");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Obtém a conexão com o banco de dados.
     *
     * @return A conexão com o banco de dados.
     */
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Trata a exceção de forma apropriada
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }
    }
}