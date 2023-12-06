package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import factory.ConnectionFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
    public boolean deleteCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getInstance().getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente deletado com sucesso!");
                deleted = true;
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    // método para atualizar cliente
    public boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET ";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            int numberOfFields = 0; // Contador para acompanhar o número de campos atualizados
            HashMap<String, String> fieldsToUpdate = new HashMap<>(); // Armazena os campos a serem atualizados

            // Adicione todos os campos que deseja permitir a atualização
            if (cliente.getNome() != null) {
                fieldsToUpdate.put("nome", cliente.getNome());
            }
            if (cliente.getCpf() != null) {
                fieldsToUpdate.put("cpf", cliente.getCpf());
            }
            if (cliente.getEndereco() != null) {
                fieldsToUpdate.put("endereco", cliente.getEndereco());
            }
            if (cliente.getEmail() != null) {
                fieldsToUpdate.put("email", cliente.getEmail());
            }
            if (cliente.getSenha() != null) {
                fieldsToUpdate.put("senha", cliente.getSenha());
            }

            for (Map.Entry<String, String> entry : fieldsToUpdate.entrySet()) {
                if (numberOfFields > 0) {
                    sql += ",";
                }
                sql += entry.getKey() + " = ?";
                numberOfFields++;
            }

            sql += " WHERE id = ?"; // Adicione a cláusula WHERE

            ps = conn.prepareStatement(sql);

            int parameterIndex = 1; // Índice para os parâmetros na declaração SQL

            for (Map.Entry<String, String> entry : fieldsToUpdate.entrySet()) {
                ps.setString(parameterIndex, entry.getValue());
                parameterIndex++;
            }

            // Adicione o ID do cliente
            ps.setInt(parameterIndex, cliente.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao atualizar o cliente:");
            System.err.println("Mensagem de erro: " + e.getMessage());
            e.printStackTrace(); // Isso imprime o rastreamento do erro no console
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
