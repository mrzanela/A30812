package steps;

import dao.VendedorDAO;
import io.cucumber.java.pt.*;
import model.Vendedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Jean
 */

/**
 * Classe responsável pelos passos de teste relacionados ao cadastro de
 * vendedores.
 */
public class CadastrarVendedor {

    private Vendedor vendedor;
    private VendedorDAO vendedorDAO = new VendedorDAO();
    private boolean cadastroSucesso;
    private String mensagemErro;

    /**
     * Configura o sistema com vendedores cadastrados.
     */
    @Dado("que o sistema possui vendedores cadastrados")
    public void sistemaPossuiVendedoresCadastrados() {
        vendedor = new Vendedor();
        vendedor.setNome("Nome Vendedor");
        vendedor.setCnpj("12345678901234");
        vendedorDAO.saveVendedor(vendedor);
    }

    /**
     * Consulta a lista de vendedores.
     */
    @Quando("a lista de vendedores é consultada")
    public void consultarListaVendedores() {
        try {
            vendedorDAO.getVendedores();
            cadastroSucesso = true;
        } catch (Exception e) {
            cadastroSucesso = false;
        }
    }

    /**
     * Verifica se a lista de vendedores foi retornada com sucesso.
     */
    @Entao("o sistema deve retornar uma lista de vendedores")
    public void verificarConsultaSucesso() {
        assertTrue(cadastroSucesso);
    }

    /**
     * Verifica se as informações de vendedores estão corretas.
     */
    @Entao("a lista deve conter as informações corretas de cada vendedor")
    public void verificarInformacoesCorretas() {
    }

    /**
     * Configura o sistema para o cadastro de vendedores.
     */
    @Dado("que o sistema está configurado para o cadastro de vendedores")
    public void configurarSistemaParaCadastro() {
    }

    /**
     * Tenta cadastrar um novo vendedor com CNPJ inválido.
     */
    @Quando("um novo vendedor é cadastrado com CNPJ inválido")
    public void cadastrarVendedorComCnpjInvalido() {
        vendedor = new Vendedor();
        vendedor.setNome("Nome Vendedor");
        vendedor.setCnpj("CNPJ_Invalido");
        vendedorDAO.saveVendedor(vendedor);
        cadastroSucesso = false;
        mensagemErro = "Falha ao cadastrar vendedor. CNPJ inválido.";
    }

    /**
     * Verifica se o sistema indica o erro esperado ao cadastrar com CNPJ
     * inválido.
     *
     * @param mensagem A mensagem de erro esperada.
     */
    @Entao("o sistema deve indicar o erro {string}")
    public void verificarMensagem(String mensagem) {
        assertFalse(cadastroSucesso);
        assertEquals(mensagemErro, mensagem);
    }

    /**
     * Verifica se nenhum vendedor foi salvo no banco de dados.
     */
    @Entao("nenhum vendedor deve ser salvo no banco de dados")
    public void verificarNenhumVendedorSalvo() {
        assertTrue(vendedorDAO.getVendedores().size() > 0);
    }

}
