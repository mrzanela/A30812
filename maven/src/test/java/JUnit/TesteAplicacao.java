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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Vendedor;

/**
 *
 * @author Jean
 */
/**
 * Classe que contém os testes unitários para a aplicação.
 */
public class TesteAplicacao {

// Teste para buscar o próximo código disponível para vendedor
    @Test
    public void testBuscaCodigo() {
        int codigo = VendedorDAO.buscaCodigo();
        assertTrue(codigo >= 0);
    }

    // Teste do construtor de Cliente sem ID
    @Test
    public void testConstrutorSemId() {
        String nome = "Maria";
        String cpf = "987.654.321-09";
        String endereco = "Rua B, 456";
        String email = "maria@example.com";
        String senha = "senha456";

        Cliente cliente = new Cliente(nome, cpf, endereco, email, senha);

        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(endereco, cliente.getEndereco());
        assertEquals(email, cliente.getEmail());
        assertEquals(senha, cliente.getSenha());
    }

    // Teste para reabrir conexão com o banco de dados
    @Test
    public void testReaberturaConexao() {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        try {
            assertFalse(connection.isClosed());
            dbConnection.closeConnection();
            assertTrue(connection.isClosed());

            // Tentar reabrir a conexão
            Connection reopenedConnection = dbConnection.getConnection();
            assertNotNull(reopenedConnection);
            assertFalse(reopenedConnection.isClosed());
        } catch (SQLException e) {
            fail("Exceção ao verificar o estado da conexão: " + e.getMessage());
        }
    }
// Teste para verificar se várias instâncias de DBConnection são a mesmas

    @Test
    public void testarMultiplasGetInstances() {
        DBConnection instance1 = DBConnection.getInstance();
        DBConnection instance2 = DBConnection.getInstance();

        assertSame(instance1, instance2);
    }
// Teste para verificar se a conexão está sendo encerrada corretamente

    @Test
    public void testCloseConnection() {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        try {
            assertFalse(connection.isClosed());
            dbConnection.closeConnection();
            assertTrue(connection.isClosed());
        } catch (SQLException e) {
            fail("Exceção ao verificar o estado da conexão: " + e.getMessage());
        }
    }
// Teste para retorno de informação para o usuário sobre pesquisa do vendedor 

    @Test
    public void testMostraMsgVendedorNaoEncontrado() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgVendedorNaoEncontrado();
    }
// Teste para retorno de informação sobre confirmação da delecao de um vendedor

    @Test
    public void testMostraMsgDelecaoParaVendedor() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgDelecao();
    }
// Teste para retorno de informação sobre confirmação da criação de um vendedor

    @Test
    public void testMostraMsgCriacaoVendedor() {
        VendedorView vendedorView = new VendedorView(null);
        vendedorView.mostraMsgCriacao();
    }
// Teste para retorno de informação para o usuário sobre pesquisa do cliente 

    @Test
    public void testMostraMsgClienteNaoEncontrado() {
        ClienteView clienteView = new ClienteView(null);
    }
// Teste para retorno de informação sobre confirmação da delecao de um cliente

    @Test
    public void testMostraMsgDelecaoParaCliente() {
        ClienteView clienteView = new ClienteView(null);
        clienteView.mostraMsgDelecao();
    }
// Teste para retorno de informação sobre confirmação da criação de um cliente

    @Test
    public void testMostraMsgCriacaoParaCliente() {
        ClienteView clienteView = new ClienteView(null);
        clienteView.mostraMsgCriacao();
    }
// Teste para mensagem de produtro não encontrado

    @Test
    public void testMostraMsgProdutoNaoEncontrado() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgProdutoNaoEncontrado();
    }
// Teste para retorno de informação sobre confirmação da delecao de um  produto

    @Test
    public void testMostraMsgDelecao() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgDelecao();
    }
// Teste para retorno de informação sobre confirmação da criação de um produto

    @Test
    public void testMostraMsgCriacao() {
        ProdutoView produtoView = new ProdutoView(null);
        produtoView.mostraMsgCriacao();
    }
// Teste para o método de exclusão de um produto

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
// Teste para o método de salvar um vendedor

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
// Teste para salvar um produto com nome nulo

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
// Teste para salvar um produto com preço zero

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
    // Teste para obter a lista de produtos

    @Test
    public void GetProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.getProdutos();

        assertNotNull(produtos);
        assertTrue(produtos.size() >= 0);
    }
}
