package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import factory.ConnectionFactory;
import java.sql.SQLException;
import model.Cliente;

public class ClienteDAO {

    public static void saveCliente(Cliente c) {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            String sql = "insert into clientes (id, nome, cpf, endereco, email, senha) values (?, ?, ?,?,?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getCpf());
            ps.setString(4, c.getEndereco());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getSenha());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }

    }

    public static List<Cliente> getClientes() {

        Connection conn = DBConnection.getInstance().getConnection();
        List<Cliente> obj = new ArrayList<>();
        try {
            String sql = "select * from clientes";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Cliente tmp = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getString("cpf"), resultSet.getString("endereco"),
                        resultSet.getString("email"), resultSet.getString("senha"));
                obj.add(tmp);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }
        return obj;

    }

    public static int buscaCodigo() {
        Connection conn = DBConnection.getInstance().getConnection();
        int id = 0;
        try {
            String sql = "select max(id) from clientes";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }
        return id;
    }

// método para deletar cliente
    public static void deleteCliente(int id) {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM clientes WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(0, id);
            System.out.println("Query SQL: " + ps); // Imprime a query SQL sendo executada

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // método para atualizar cliente
    public void updateCliente(Cliente cliente) {
        //#region
//        String sql = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, email = ?, senha = ?, dataCadastro = ? WHERE id = ?";
//
//        Connection conn = null;
//        PreparedStatement pstm = null;
//
//        try {
//            conn = ConnectionFactory.createConnectionToMySQL();
//            pstm = conn.prepareStatement(sql);
//
//            pstm.setString(1, cliente.getNome());
//            pstm.setString(2, cliente.getCpf());
//            pstm.setString(3, cliente.getEndereco());
//            pstm.setString(4, cliente.getEmail());
//            pstm.setString(5, cliente.getSenha());
//            pstm.setDate(6, new Date(cliente.getDataCadastro().getTime()));
//            pstm.setInt(7, cliente.getId());
//
//            // Executar a query de atualização
//            int rowsAffected = pstm.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Cliente atualizado com sucesso!");
//            } else {
//                System.out.println("Cliente com ID " + cliente.getId() + " não encontrado.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (pstm != null) {
//                    pstm.close();
//                }
//
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//#endregion
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, email = ?, senha = ?, dataCadastro = ? WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getSenha());
            ps.setInt(6, cliente.getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente com ID " + cliente.getId() + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
