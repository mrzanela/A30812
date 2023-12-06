package controller;

import dao.ClienteDAO;
import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import view.ClienteView;

/**
 *
 * @author Sâmeck
 */
public class ClienteController {

    private Cliente c;
    private ClienteView cv;
    private List<Cliente> clientes;

    public ClienteController(ClienteView cv, List<Cliente> clientes) {
        this.cv = cv;
        this.clientes = clientes;
    }

    public boolean criaCliente() {
        boolean status = false;
        int id = ClienteDAO.buscaCodigo() + 1;
        this.c = cv.inputData(id);
        if (this.c != null) {
            clientes.add(c);
            ClienteDAO.saveCliente(c);
            status = true;
        }
        return status;
    }

    public boolean deletaCliente() {
        int id = cv.defineCliente();
        boolean deleted = false;

        if (id > 0) {
            ClienteDAO dao = new ClienteDAO(); // Criar uma instância de ClienteDAO
            deleted = dao.deleteCliente(id); // Chamar o método de instância deleteCliente
            if (deleted) {        
            }
        } else {
            System.out.println("ID inválido.");
        }

        return deleted;
    }

//    public int buscaIdClientePorId(int id) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int clienteId = -1; // Valor padrão para indicar que o cliente não foi encontrado
//
//        try {
//            conn = DBConnection.getInstance().getConnection();
//            String sql = "SELECT id FROM clientes WHERE id = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                clienteId = rs.getInt("id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return clienteId;
//    }

    public boolean atualizarCliente() {
        Cliente clienteParaAtualizar = cv.inputDataForUpdate();
        boolean atualizado = atualizarClienteNoBanco(clienteParaAtualizar);

        if (atualizado) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o cliente.");
        }
        return atualizado;
    }

    private boolean atualizarClienteNoBanco(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.atualizarCliente(cliente);
    }

}
