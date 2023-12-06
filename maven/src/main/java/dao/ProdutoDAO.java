package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Produto;

public class ProdutoDAO {

    //método para criar um vendedor
    public static void saveProduto(Produto p) {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            String sql = "insert into produtos (id, nome, preco, qtd) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNome());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getQtd());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados:");
            e.printStackTrace();
        }

    }

    //método para listar os produtos
    public static List<Produto> getProdutos() {

        Connection conn = DBConnection.getInstance().getConnection();
        List<Produto> obj = new ArrayList<>();
        try {
            String sql = "select * from produtos";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Produto tmp = new Produto(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getDouble("preco"), resultSet.getInt("qtd"));
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
            String sql = "select max(id) from produtos";
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
    public boolean deleteProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getInstance().getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
                deleted = true;
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    // método para atualizar Produto
    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET ";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            int numberOfFields = 0; // Contador para acompanhar o número de campos atualizados
            HashMap<String, Object> fieldsToUpdate = new HashMap<>(); // Armazena os campos a serem atualizados

            // Adicione todos os campos que deseja permitir a atualização
            if (produto.getNome() != null) {
                fieldsToUpdate.put("nome", produto.getNome());
            }
            if (produto.getPreco() != 0.0) {
                fieldsToUpdate.put("preco", produto.getPreco());
            }
            if (produto.getQtd() != 0) {
                fieldsToUpdate.put("qtd", produto.getQtd());
            }

            for (Map.Entry<String, Object> entry : fieldsToUpdate.entrySet()) {
                if (numberOfFields > 0) {
                    sql += ",";
                }
                sql += entry.getKey() + " = ?";
                numberOfFields++;
            }

            sql += " WHERE id = ?"; // Adicione a cláusula WHERE

            ps = conn.prepareStatement(sql);

            int parameterIndex = 1; // Índice para os parâmetros na declaração SQL

            for (Map.Entry<String, Object> entry : fieldsToUpdate.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Double) {
                    ps.setDouble(parameterIndex, (Double) value);
                } else {
                    ps.setString(parameterIndex, value.toString());
                }
                parameterIndex++;
            }

            // Adicione o ID do produto
            ps.setInt(parameterIndex, produto.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao atualizar o produto:");
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
