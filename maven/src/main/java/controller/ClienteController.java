package controller;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import view.ClienteView;

/**
 * Controlador responsável por gerenciar operações relacionadas aos clientes.
 * Interage com ClienteView e ClienteDAO
 * Utilizado pela classe AppController.
 * 
 * @author Sâmeck
 */
public class ClienteController {

    private Cliente c;
    private ClienteView cv;
    private List<Cliente> clientes;

    /**
     * Construtor do ClienteController.
     *
     * @param cv       Instância da classe ClienteView.
     * @param clientes Lista de clientes gerenciada pelo controlador.
     */

    public ClienteController(ClienteView cv, List<Cliente> clientes) {
        this.cv = cv;
        this.clientes = clientes;
    }

    /**
     * Cria novo cliente com base nos dados inseridos pelo usuário.
     *
     * @return true se o cliente foi criado com sucesso, false caso contrário.
     */

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

    /**
     * Deleta um cliente com base no ID fornecido pelo usuário.
     *
     * @return true se o cliente foi deletado com sucesso, false caso contrário.
     */

    public boolean deletaCliente() {
        int id = cv.defineCliente();
        boolean deleted = false;

        if (id > 0) {
            ClienteDAO dao = new ClienteDAO(); // Criar uma instância de ClienteDAO
            deleted = dao.deleteCliente(id); // Chamar o método de instância deleteCliente
            if (deleted) {
                // Lógica para tratamento após a exclusão.
            }
        } else {
            System.out.println("ID inválido.");
        }

        return deleted;
    }

    /**
     * Atualiza um cliente com base nos dados inseridos pelo usuário.
     *
     * @return true se o cliente foi atualizado com sucesso, false caso contrário.
     */

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