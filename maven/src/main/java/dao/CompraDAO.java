package dao;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.util.ArrayList;
// import java.util.List;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// import model.Cliente;
// import model.Compra;
// import model.ItemCompra;

// public class CompraDAO {

//     private Connection connection;

//     public CompraDAO(Connection connection) {
//         this.connection = connection;
//     }

//     public void salvarCompra(Compra compra) {
//         String sqlCompra = "INSERT INTO compra (cliente_id, valor_total) VALUES (?, ?)";

//         try (PreparedStatement statementCompra = connection.prepareStatement(sqlCompra, PreparedStatement.RETURN_GENERATED_KEYS)) {
//             statementCompra.setInt(1, compra.getCliente().getId());
//             statementCompra.setDouble(2, compra.getValorTotal());
//             statementCompra.executeUpdate();

//             ResultSet resultSet = statementCompra.getGeneratedKeys();
//             if (resultSet.next()) {
//                 int compraId = resultSet.getInt(1);
//                 compra.setId(compraId);

//                 // Salvar itens da compra usando ItemCompraDAO
//                 ItemCompraDAO itemCompraDAO = new ItemCompraDAO(connection);
//                 itemCompraDAO.salvarItensCompra(compraId, compra.getItens());
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public List<Compra> listarCompras() {
//         List<Compra> compras = new ArrayList<>();
//         String sql = "SELECT * FROM compra";

//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             ResultSet resultSet = statement.executeQuery();

//             while (resultSet.next()) {
//                 int compraId = resultSet.getInt("id");
//                 int clienteId = resultSet.getInt("cliente_id");

//                 // Recuperar cliente
//                 ClienteDAO clienteDAO = new ClienteDAO(connection);
//                 Cliente cliente = clienteDAO.getClientes(clienteId);

//                 // Recuperar itens da compra usando ItemCompraDAO
//                 ItemCompraDAO itemCompraDAO = new ItemCompraDAO(connection);
//                 List<ItemCompra> itens = itemCompraDAO.recuperarItensCompra(compraId);

//                 Compra compra = new Compra();
//                 compra.setId(compraId);
//                 compra.setCliente(cliente);
//                 compra.setItens(itens);
//                 compra.calcularValorTotal();  // Calcular valor total

//                 compras.add(compra);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return compras;
//     }
// }
