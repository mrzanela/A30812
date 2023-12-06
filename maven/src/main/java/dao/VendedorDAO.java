package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Vendedor;

public class VendedorDAO {

    //método para criar um vendedor
    public static void saveVendedor(Vendedor v) {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            String sql = "insert into vendedores (id, nome, cnpj, endereco, email, senha) values (?, ?, ?,?,?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, v.getId());
            ps.setString(2, v.getNome());
            ps.setString(3, v.getCnpj());
            ps.setString(4, v.getEndereco());
            ps.setString(5, v.getEmail());
            ps.setString(6, v.getSenha());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }

    }

    //método para listar os vendedores
    public static List<Vendedor> getVendedores() {

        Connection conn = DBConnection.getInstance().getConnection();
        List<Vendedor> obj = new ArrayList<>();
        try {
            String sql = "select * from vendedores";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Vendedor tmp = new Vendedor(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getString("cnpj"), resultSet.getString("endereco"),
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
            String sql = "select max(id) from vendedores";
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

    // método para deletar vendedor
    public boolean deleteVendedor(int id) {
        String sql = "DELETE FROM vendedores WHERE id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getInstance().getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vendedor deletado com sucesso!");
                deleted = true;
            } else {
                System.out.println("Vendedor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    // método para atualizar vendedor
    public boolean atualizarVendedor(Vendedor vendedor) {
        String sql = "UPDATE vendedores SET ";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            int numberOfFields = 0; // Contador para acompanhar o número de campos atualizados
            HashMap<String, String> fieldsToUpdate = new HashMap<>(); // Armazena os campos a serem atualizados

            // Adicione todos os campos que deseja permitir a atualização
            if (vendedor.getNome() != null) {
                fieldsToUpdate.put("nome", vendedor.getNome());
            }
            if (vendedor.getCnpj() != null) {
                fieldsToUpdate.put("cnpj", vendedor.getCnpj());
            }
            if (vendedor.getEndereco() != null) {
                fieldsToUpdate.put("endereco", vendedor.getEndereco());
            }
            if (vendedor.getEmail() != null) {
                fieldsToUpdate.put("email", vendedor.getEmail());
            }
            if (vendedor.getSenha() != null) {
                fieldsToUpdate.put("senha", vendedor.getSenha());
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
            ps.setInt(parameterIndex, vendedor.getId());

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
