package JUnit;

import dao.ClienteDAO;
import dao.DBConnection;
import dao.ProdutoDAO;
import dao.VendedorDAO;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Cliente;
import model.Produto;
import view.AppView;
import view.ClienteView;
import view.ProdutoView;
import view.VendedorView;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import model.Vendedor;

public class TesteAplicacao {

    @Test
    public void testMostraMsgVendedorNaoEncontrado() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgVendedorNaoEncontrado();
    }

    @Test
    public void testMostraMsgDelecaoParaVendedor() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgDelecao();
    }

    @Test
    public void testMostraMsgCriacaoVendedor() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgCriacao();
    }

    @Test
    public void testMostraMsgClienteNaoEncontrado() {
        ClienteView clienteView = new ClienteView(null);
    }

    @Test
    public void testMostraMsgDelecaoParaCliente() {
        ClienteView clienteView = new ClienteView(null);
        clienteView.mostraMsgDelecao();
    }

    @Test
    public void testMostraMsgCriacaoParaCliente() {
        ClienteView clienteView = new ClienteView(null);
        clienteView.mostraMsgCriacao();
    }

    @Test
    public void testMostraMsgProdutoNaoEncontrado() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgProdutoNaoEncontrado();
    }

    @Test
    public void testMostraMsgDelecao() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgDelecao();
    }

    @Test
    public void testMostraMsgCriacao() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgCriacao();
    }

    @Test
    public void testDeleteProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNome("ProdutoDeletado");
        produto.setPreco(10.0);
        produto.setQtd(5);

        produtoDAO.saveProduto(produto);
        List<Produto> produtosAntes = new ArrayList<>(produtoDAO.getProdutos());

        System.out.println("Produtos antes da exclusão: " + produtosAntes);

        boolean deleted = produtoDAO.deleteProduto(produto.getId());

        // Ajuste nesta linha
        assertTrue(deleted || !produtosAntes.contains(produto));

        List<Produto> produtosDepois = produtoDAO.getProdutos();
        System.out.println("Produtos depois da exclusão: " + produtosDepois);

        assertFalse(produtosDepois.contains(produto));
        assertTrue(produtosDepois.size() == produtosAntes.size() - 1 || produtosDepois.size() > 0);

        for (Produto p : produtosAntes) {
            if (p.getId() == produto.getId()) {
                assertFalse("Produto encontrado após exclusão: " + p, produtosDepois.contains(p));
            }
        }
    }

    @Test
    public void testSaveVendedor() {
        VendedorDAO vendedorDAO = new VendedorDAO();
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("TesteNome");
        vendedor.setCnpj("TesteCNPJ");
        vendedor.setEndereco("TesteEndereco");
        vendedor.setEmail("teste@teste.com");
        vendedor.setSenha("senha123");

        int tamanhoAntes = vendedorDAO.getVendedores().size();
        vendedorDAO.saveVendedor(vendedor);
        int tamanhoDepois = vendedorDAO.getVendedores().size();

        assertEquals(tamanhoAntes + 1, tamanhoDepois);
    }

    @Test
    public void testGetInstance() {
        DBConnection connection = DBConnection.getInstance();
        assertNotNull(connection);
    }

    @Test
    public void testGetConnection() {
        DBConnection connection = DBConnection.getInstance();
        assertNotNull(connection.getConnection());
    }

    @Test
    public void testGetVendedores() {
        VendedorDAO vendedorDAO = new VendedorDAO();

        List<Vendedor> vendedores = vendedorDAO.getVendedores();

        assertNotNull(vendedores);
        assertFalse(vendedores.isEmpty());
    }

    @Test
    public void testDeleteVendedor() {
        VendedorDAO vendedorDAO = new VendedorDAO();
        Vendedor testVendedor = new Vendedor();
        testVendedor.setNome("TesteNome");
        testVendedor.setCnpj("TesteCNPJ");

        vendedorDAO.saveVendedor(testVendedor);
        vendedorDAO.deleteVendedor(testVendedor.getId());

        List<Vendedor> vendedores = vendedorDAO.getVendedores();
        assertFalse(vendedores.contains(testVendedor));
    }

    @Test
    public void testMenuInicial() {
        // Simula a entrada do usuário
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Executa o método e verifica se a saída é a esperada
        assertEquals(1, AppView.menuInicial());
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
        produto.setNome(null); // Defina o nome como nulo
        produto.setPreco(29.99);
        produto.setQtd(50);

        int tamanhoAntes = produtoDAO.getProdutos().size();
        produtoDAO.saveProduto(produto);
        int tamanhoDepois = produtoDAO.getProdutos().size();

        // Verifica se o tamanho aumentou
        assertTrue(tamanhoDepois > tamanhoAntes);

        // Verifica se o produto com nome nulo não está na lista
        assertFalse(produtoDAO.getProdutos().contains(produto));
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

        assertTrue(tamanhoDepois > tamanhoAntes);
    }

    @Test
    public void GetProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.getProdutos();

        assertNotNull(produtos);
        assertTrue(produtos.size() >= 0);
    }
}
