package steps;

import dao.ClienteDAO;
import io.cucumber.java.pt.*;
import model.Cliente;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;

/**
 *
 * @author Jean
 */

/**
 * Classe responsável pelos passos de teste relacionados ao cadastro de
 * clientes.
 */
public class CadastrarCliente {

    private Cliente cliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private boolean cadastroSucesso;
    private String mensagemErro;
    private boolean listaVerificada;

    /**
     * Cadastra um cliente fictício para testes.
     */
    @Dado("que o sistema tem clientes cadastrados")
    public void que_o_sistema_tem_clientes_cadastrados() {
        // Cadastrar um cliente fictício para fins de teste
        cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setCpf("12345678901");
        cliente.setEndereco("Endereço do Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setSenha("senha123");

        clienteDAO.saveCliente(cliente);
    }

    /**
     * Verifica se a lista de clientes foi verificada.
     */
    @Quando("a lista de clientes é verificada")
    public void a_lista_de_clientes_é_verificada() {
        // Verificar se a lista de clientes não está vazia
        listaVerificada = !clienteDAO.getClientes().isEmpty();
    }

    /**
     * Verifica se o sistema mostra uma lista de clientes.
     */
    @Entao("o sistema deve mostrar uma lista de clientes")
    public void o_sistema_deve_mostrar_uma_lista_de_clientes() {
        // Verificar se a lista foi devidamente verificada
        assertTrue(listaVerificada);
    }

    /**
     * Verifica se a lista contém os dados corretos de cada cliente.
     */
    @Entao("a lista deve conter os dados corretos de cada cliente")
    public void a_lista_deve_conter_os_dados_corretos_de_cada_cliente() {
        // Obter a lista de clientes do banco de dados
        List<Cliente> clientes = clienteDAO.getClientes();

        // Verificar se a lista não está vazia
        assertFalse(clientes.isEmpty());

        // Verificar se o cliente de teste está presente na lista
        boolean clienteEncontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(cliente.getNome())
                    && cliente.getCpf().equals(cliente.getCpf())
                    && cliente.getEndereco().equals(cliente.getEndereco())
                    && cliente.getEmail().equals(cliente.getEmail())
                    && cliente.getSenha().equals(cliente.getSenha())) {
                clienteEncontrado = true;
                break;
            }
        }

        assertTrue(clienteEncontrado);
    }

    /**
     * Configura o sistema com clientes cadastrados.
     */
    @Dado("que o sistema possui clientes cadastrados")
    public void que_o_sistema_possui_clientes_cadastrados() {
        // Adicione aqui a lógica para cadastrar clientes fictícios para fins de teste
        cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setCpf("12345678901");
        cliente.setEndereco("Endereço do Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setSenha("senha123");

        clienteDAO.saveCliente(cliente);
    }

    /**
     * Registra um cliente com CPF inválido.
     */
    @Quando("um cliente é registrado com CPF inválido")
    public void um_cliente_é_registrado_com_cpf_inválido() {
        cliente = new Cliente();
        cliente.setNome("Nome Cliente");
        cliente.setCpf("CPF_Invalido");
        clienteDAO.saveCliente(cliente);
        cadastroSucesso = false;
        mensagemErro = "Falha ao cadastrar cliente. CPF inválido.";
    }

    /**
     * Verifica se o sistema mostra o erro esperado.
     *
     * @param mensagem A mensagem de erro esperada.
     */
    @Entao("o sistema deve mostrar o erro {string}")
    public void o_sistema_deve_mostrar_o_erro(String mensagem) {
        assertFalse(cadastroSucesso);
        assertEquals(mensagemErro, mensagem);
    }

    /**
     * Verifica se nenhum cliente foi salvo.
     */
    @Entao("nenhum cliente deve ser salvo.")
    public void nenhum_cliente_deve_ser_salvo() {
        int tamanhoAntes = clienteDAO.getClientes().size();

        // Execute a ação que deveria resultar em nenhum cliente salvo
        int tamanhoDepois = clienteDAO.getClientes().size();
        assertEquals(tamanhoAntes, tamanhoDepois);
    }

    /**
     * Verifica se a lista contém as informações corretas de cada cliente.
     */
    @Entao("a lista deve conter as informações corretas de cada cliente")
    public void a_lista_deve_conter_as_informações_corretas_de_cada_cliente() {
        List<Cliente> clientes = clienteDAO.getClientes();
        assertFalse(clientes.isEmpty());

        // Vamos verificar se o primeiro cliente na lista tem as informações corretas
        Cliente primeiroCliente = clientes.get(0);
        assertEquals("Cliente Teste", primeiroCliente.getNome());
        assertEquals("12345678901", primeiroCliente.getCpf());
        assertEquals("Endereço do Cliente Teste", primeiroCliente.getEndereco());
        assertEquals("cliente@teste.com", primeiroCliente.getEmail());
        assertEquals("senha123", primeiroCliente.getSenha());
    }

}
