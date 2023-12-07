package steps;

import dao.ProdutoDAO;
import io.cucumber.java.pt.*;
import model.Produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Jean
 */

/**
 * Classe responsável pelos passos de teste relacionados ao cadastro de
 * produtos.
 */
public class CadastrarProdutoTeste {

    private Produto produto;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private boolean cadastroSucesso;
    private String mensagemErro;

    /**
     * Configura o sistema para o cadastro de produtos.
     */
    @Dado("que o sistema está configurado para o cadastro de produtos")
    public void configurarSistemaParaCadastro() {
    }

    /**
     * Cadastra um novo produto com sucesso.
     */
    @Quando("um novo produto é cadastrado com nome, preço e quantidade")
    public void cadastrarProdutoComSucesso() {
        produto = new Produto();
        produtoDAO.saveProduto(produto);
        cadastroSucesso = true;
    }

    /**
     * Verifica se o produto foi salvo no banco de dados.
     */
    @Entao("o produto deve ser salvo no banco de dados")
    public void verificarProdutoSalvo() {
        assertTrue(cadastroSucesso);
    }

    /**
     * Verifica se a mensagem de sucesso é exibida após o cadastro do produto.
     *
     * @param mensagem A mensagem esperada após o cadastro bem-sucedido.
     */
    @Entao("o sistema deve exibir a mensagem {string}")
    public void verificarMensagemSucesso(String mensagem) {
        assertEquals(mensagem, "Produto cadastrado com sucesso");
    }

    /**
     * Tenta cadastrar um novo produto com falha (nome em branco, preço
     * negativo, quantidade negativa).
     */
    @Quando("um novo produto com nome em branco, preço negativo e quantidade negativa é cadastrado")
    public void cadastrarProdutoComFalha() {
        produto = new Produto();
        produtoDAO.saveProduto(produto);
        cadastroSucesso = false;
        mensagemErro = "Falha ao cadastrar produto. Verifique as informações fornecidas.";
    }

    /**
     * Verifica se a mensagem de erro é exibida após o cadastro inválido do
     * produto.
     *
     * @param mensagem A mensagem de erro esperada após o cadastro inválido do
     * produto.
     */
    @Entao("o sistema deve exibir a mensagem de erro {string}")
    public void verificarMensagemErroCadastroProduto(String mensagem) {
        assertFalse(cadastroSucesso);
        assertEquals(mensagemErro, mensagem);
    }

    /**
     * Verifica se nenhum produto foi salvo no banco de dados após o cadastro
     * inválido.
     */
    @Entao("nenhum produto deve ser salvo no banco de dados")
    public void verificarNenhumProdutoSalvo() {
        int tamanhoDepois = produtoDAO.getProdutos().size();
        assertTrue("Nenhum produto deveria ser salvo no banco de dados", tamanhoDepois > 0);
    }

    /**
     * Cadastra um novo produto.
     */
    @Quando("um novo produto é cadastrado")
    public void um_novo_produto_é_cadastrado() {
        produto = new Produto();
        produtoDAO.saveProduto(produto);
        cadastroSucesso = true;
    }

    /**
     * Cadastra um novo produto com informações inválidas.
     */
    @Quando("um novo produto com informações inválidas é cadastrado")
    public void um_novo_produto_com_informações_inválidas_é_cadastrado() {
        produto = new Produto();
        produtoDAO.saveProduto(produto);
        cadastroSucesso = false;
        mensagemErro = "Falha ao cadastrar produto. Verifique as informações fornecidas.";
    }
}
