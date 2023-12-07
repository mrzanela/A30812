package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Cliente;

/**
 * Classe que fornece métodos de acesso a dados para a entidade Cliente.
 * Interage com o banco de dados para salvar, recuperar, buscar códigos, deletar
 * e atualizar clientes.
 * Utiliza DBConnection para gerenciar a conexão com o banco de dados.
 * Utilizada pelo ClienteController.
 * 
 * @author Sâmeck
 */

public class ClienteDAO {

    /**
     * Salva um cliente no banco de dados.
     *
     * @param c O objeto Cliente a ser salvo.
     */
    public static void saveCliente(Cliente c) {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into clientes (id, nome, cpf, endereco, email, senha) values (?, ?, ?,?,?, ?)";
            ps = conn.prepareStatement(sql);
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
        } finally {
            DBConnection.getInstance().closeConnection();
        }

    }

    /**
     * Obtém a lista de todos os clientes do banco de dados.
     *
     * @return Uma lista de objetos Cliente.
     */
    public static List<Cliente> getClientes() {

        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Cliente> obj = new ArrayList<>();
        try {
            String sql = "select * from clientes";
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Cliente tmp = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getString("cpf"), resultSet.getString("endereco"),
                        resultSet.getString("email"), resultSet.getString("senha"));
                obj.add(tmp);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        } finally {
            DBConnection.getInstance().closeConnection();
        }
        return obj;

    }

    /**
     * Busca o código máximo (ID) dos clientes no banco de dados.
     *
     * @return O código máximo encontrado.
     */
    public static int buscaCodigo() {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            String sql = "select max(id) from clientes";
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        } finally {
            DBConnection.getInstance().closeConnection();
        }
        return id;
    }

    // método para deletar cliente
    public boolean deleteCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)) {

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
        } finally {
            DBConnection.getInstance().closeConnection();
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

            sql += " WHERE id = ?";

            ps = conn.prepareStatement(sql);

            int parameterIndex = 1; // Índice para os parâmetros na declaração SQL

            for (Map.Entry<String, String> entry : fieldsToUpdate.entrySet()) {
                ps.setString(parameterIndex, entry.getValue());
                parameterIndex++;
            }

            // Adicionar o ID do cliente
            ps.setInt(parameterIndex, cliente.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao atualizar o cliente:");
            System.err.println("Mensagem de erro: " + e.getMessage());
            e.printStackTrace(); // Imprime o rastreamento do erro no console
            return false;
        } finally {
            DBConnection.getInstance().closeConnection();
        }
    }

}
