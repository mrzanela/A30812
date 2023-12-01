package JUnit;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendedorDAO;
import org.junit.Test;
import static org.junit.Assert.*;

import model.Cliente;
import model.Produto;

import java.util.List;

import aplicacao.aplicacao;

public class TesteAplicacao {

    @Test
    public void CadastrarNovoCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        int tamanhoAntes = clienteDAO.getClientes().size();

        aplicacao.cadastrarNovoCliente();

        int tamanhoDepois = clienteDAO.getClientes().size();
        assertEquals(tamanhoAntes + 1, tamanhoDepois);
    }

    @Test
    public void ExibirClientes() { // Testar se o método não gera exceções.
        aplicacao.exibirClientes();
    }

    @Test
    public void CadastrarNovoVendedor() {
        VendedorDAO vendedorDAO = new VendedorDAO();
        int tamanhoAntes = vendedorDAO.getVendedores().size();

        aplicacao.cadastrarNovoVendedor();

        int tamanhoDepois = vendedorDAO.getVendedores().size();
        assertEquals(tamanhoAntes + 1, tamanhoDepois);
    }

    @Test
    public void ExibirVendedores() {// Testar se o método não gera exceções.
        aplicacao.exibirVendedores();
    }

    @Test
    public void CadastrarNovoProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int tamanhoAntes = produtoDAO.getProdutos().size();

        aplicacao.cadastrarNovoProduto();

        int tamanhoDepois = produtoDAO.getProdutos().size();
        assertEquals(tamanhoAntes + 1, tamanhoDepois);
    }

    @Test
    public void ExibirProdutos() {// Testar se o método não gera exceções.
        aplicacao.exibirProdutos();
    }

    @Test
    public void CadastrarNovoClienteComInformacoesInvalidas() {
        ClienteDAO clienteDAO = new ClienteDAO();
        int tamanhoAntes = clienteDAO.getClientes().size();

        aplicacao.cadastrarNovoClienteComInformacoesInvalidas();

        int tamanhoDepois = clienteDAO.getClientes().size();
        assertEquals(tamanhoAntes, tamanhoDepois);
    }

    @Test
    public void GetClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();

        // Recupera a lista de clientes do banco de dados
        List<Cliente> clientes = clienteDAO.getClientes();

        // Verifica se a lista não é nula
        assertNotNull(clientes);
    }

    @Test
    public void SaveProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(29.99);
        produto.setQtd(50);

        int tamanhoAntes = produtoDAO.getProdutos().size();
        produtoDAO.saveProduto(produto);
        int tamanhoDepois = produtoDAO.getProdutos().size();

        assertEquals(tamanhoAntes + 1, tamanhoDepois);
    }

    @Test
    public void SaveProdutoComNomeNulo() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setPreco(29.99);
        produto.setQtd(50);

        int tamanhoAntes = produtoDAO.getProdutos().size();
        produtoDAO.saveProduto(produto);
        int tamanhoDepois = produtoDAO.getProdutos().size();

        assertEquals(tamanhoAntes, tamanhoDepois);
    }

    @Test
    public void SalvarProdutoComPrecoZero() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNome("Calça");
        produto.setPreco(0);
        produto.setQtd(20);

        int tamanhoAntes = produtoDAO.getProdutos().size();
        produtoDAO.saveProduto(produto);
        int tamanhoDepois = produtoDAO.getProdutos().size();

        assertEquals(tamanhoAntes, tamanhoDepois);
    }

    @Test
    public void GetProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.getProdutos();

        assertNotNull(produtos);
        assertTrue(produtos.size() >= 0);
    }
}
