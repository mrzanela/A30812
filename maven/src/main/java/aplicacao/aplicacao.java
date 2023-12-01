package aplicacao;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendedorDAO;
import model.Cliente;
import model.Produto;
import model.Vendedor;

import java.util.Date;

public class aplicacao {

    // adicionando cliente
    public static void cadastrarNovoCliente() {
        ClienteDAO clienteDao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setNome("teste123");
        cliente.setCpf("001.111.222-33");
        cliente.setEndereco("R. Andorinhas, 128, Betim-MG");
        cliente.setEmail("aa@gmail.com");
        cliente.setSenha("aaaa");
        cliente.setDataCadastro(new Date());
        clienteDao.saveCliente(cliente);
    }

    // listando clientes
    public static void exibirClientes() {
        ClienteDAO clienteDao = new ClienteDAO();
        for (Cliente c : clienteDao.getClientes()) {
            System.out.println("Cliente: " + c.getNome());
        }
    }

    // deletar cliente
    public static void deletarCliente() {

        // id do cliente para ser deletado
        int idClienteDel = 10;

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.deleteCLiente(idClienteDel);

    }

    // atualizar cliente
    public static void attCliente() {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(11);
        clienteAtualizado.setEmail("tes@teste");
        clienteAtualizado.setSenha("123");

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.updateCliente(clienteAtualizado);

    }

    // cadastrando novo vendedor
    public static void cadastrarNovoVendedor() {
        VendedorDAO vendedorDAO = new VendedorDAO();
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("teadsada");
        vendedor.setCnpj("00.000.000/0001-00");
        vendedor.setEndereco("R. Xistao, 001, São Paulo-SP");
        vendedor.setEmail("vendedor@gmail.com");
        vendedor.setSenha("Vendomui3");
        vendedor.setDataCadastro(new Date());
        vendedorDAO.saveVendedor(vendedor);
    }

    public static void deletarVendedor() {

        // id do cliente para ser deletado
        int idVendedorDel = 4;

        VendedorDAO vendedorDAO = new VendedorDAO();
        vendedorDAO.deleteVendedor(idVendedorDel);

    }

    // listar vendedores
    public static void exibirVendedores() {
        VendedorDAO vendedorDAO = new VendedorDAO();
        for (Vendedor v : vendedorDAO.getVendedores()) {
            System.out.println("Vendedor: " + v.getNome());
        }
    }

    // método para atualizar vendedores

    public static void attVendedor() {
        Vendedor vendedorAtualizado = new Vendedor();
        vendedorAtualizado.setId(3);
        vendedorAtualizado.setNome("Jean");
        vendedorAtualizado.setSenha("123");

        VendedorDAO vendedorDAO = new VendedorDAO();
        vendedorDAO.updateVendedor(vendedorAtualizado);

    }

    // cadastrar produto
    public static void cadastrarNovoProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNome("Chuteira");
        produto.setPreco(117.50);
        produto.setQtd(5);
        produtoDAO.saveProduto(produto);
    }

    // listar produtos
    public static void exibirProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (Produto p : produtoDAO.getProdutos()) {
            System.out.println("Produto: " + p.getNome());
        }
    }

    // deletar produto
    public static void deletarProduto() {
        // id do produto a ser deletado
        int idProduto = 7;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.deleteProduto(idProduto);
    }

    // atualizar produtos
    public static void attProduto() {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(9);
        produtoAtualizado.setNome("Chuteira Nice");
        produtoAtualizado.setPreco(120.50);
        produtoAtualizado.setQtd(5);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.updateProduto(produtoAtualizado);
    }

    public static void cadastrarNovoClienteComInformacoesInvalidas() {
        ClienteDAO clienteDao = new ClienteDAO();
        Cliente cliente = new Cliente();

        cliente.setNome("");
        cliente.setCpf("123");
        cliente.setEndereco("");
        cliente.setEmail("email@outlook.com");
        cliente.setSenha("zedamanga");

        try {
            clienteDao.saveCliente(cliente);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente com informações inválidas: " + e.getMessage());
        }
    }
}
