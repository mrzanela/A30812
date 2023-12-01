package dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import factory.ConnectionFactory;
// import model.Compra;
// import model.ItemCompra;
// import model.Produto;

// public class ItemCompraDAO {
//      private Connection connection;

//     public ItemCompraDAO(Connection connection) {
//         this.connection = connection;
//     }

//     public void salvarItensCompra(int compraId, List<ItemCompra> itens) {
//         String sql = "INSERT INTO item_compra (compra_id, produto_id, quantidade) VALUES (?, ?, ?)";

//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             for (ItemCompra item : itens) {
//                 statement.setInt(1, compraId);
//                 statement.setInt(2, item.getProduto().getId());
//                 statement.setInt(3, item.getQtd());
//                 statement.executeUpdate();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public List<ItemCompra> recuperarItensCompra(int compraId) {
//         List<ItemCompra> itens = new ArrayList<>();
//         String sql = "SELECT * FROM item_compra WHERE compra_id = ?";

//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, compraId);
//             ResultSet resultSet = statement.executeQuery();

//             while (resultSet.next()) {
//                 int produtoId = resultSet.getInt("produto_id");
//                 int quantidade = resultSet.getInt("quantidade");

//                 // Recuperar produto
//                 ProdutoDAO produtoDAO = new ProdutoDAO(conn);
//                 Produto produto = produtoDAO.recuperarProdutoPorId(produtoId);
//                 Produto produto = produtoDAO.getProdutos(produtoId);

//                 ItemCompra item = new ItemCompra();
//                 item.setProduto(produto);
//                 item.setQuantidade(quantidade);

//                 itens.add(item);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return itens;
//     }
// }
