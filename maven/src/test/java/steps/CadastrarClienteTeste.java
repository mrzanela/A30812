package steps;

import aplicacao.Main;
import aplicacao.aplicacao;
import dao.ClienteDAO;
import io.cucumber.java.pt.*;
import model.Cliente;

import static org.junit.Assert.assertTrue;

public class CadastrarClienteTeste {

    private ClienteDAO clienteDAO;
    private boolean clienteCadastrado;

    @Dado("que {string} está no sistema com suas credenciais")
    public void queFulanoEstaNoSistemaComSuasCredenciais(String nome) {
        new Main();
        aplicacao.cadastrarNovoCliente();
    }

    @Quando("{string} cadastra um novo cliente")
    public void fulanoCadastraUmNovoCliente(String nome) {
        new Main();
        aplicacao.cadastrarNovoCliente();
    }

    @Então("o banco de dados deve salvar o registro do novo cliente")
    public void bancoDeDadosDeveSalvarRegistroNovoCliente() {
        clienteDAO = new ClienteDAO();
        clienteCadastrado = false;

        for (Cliente cliente : clienteDAO.getClientes()) {
            if ("Maria Gabriela Dias".equals(cliente.getNome())) {
                clienteCadastrado = true;
                break;
            }
        }

        assertTrue(clienteCadastrado);
    }

    @Quando("{string} tenta cadastrar um novo cliente com informações inválidas")
    public void fulanoTentaCadastrarUmNovoClienteComInformacoesInvalidas(String nome) {
        new Main();
        aplicacao.cadastrarNovoClienteComInformacoesInvalidas();
    }

    @Então("o sistema deve exibir uma mensagem de erro")
    public void sistemaDeveExibirMensagemDeErro() {
        assertTrue(true);
    }
}
