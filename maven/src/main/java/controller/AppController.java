package controller;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import java.util.List;
import model.Cliente;
import view.AppView;
import view.ClienteView;
import dao.VendedorDAO;
import model.Produto;
import model.Vendedor;
import view.ProdutoView;
import view.VendedorView;

/**
 * Controlador principal da aplicação.
 * Responsável por gerenciar clientes, vendedores, produtos e as operações
 * relacionadas a cada um deles.
 * Ele inicia a aplicação.
 *
 * @author Sâmeck
 */

public class AppController {

    // Lista de clientes
    private List<Cliente> clientes;
    private ClienteView cv;
    private ClienteController cc;
    private Cliente c;

    // Lista de vendedores
    private List<Vendedor> vendedores;
    private VendedorView vv;
    private VendedorController vc;
    private Vendedor v;

    // Lista de produtos
    private List<Produto> produtos;
    private ProdutoView pv;
    private ProdutoController pc;
    private Produto p;

    /**
     * Construtor da classe AppController. Inicializa as listas e as views
     * necessárias.
     */
    public AppController() {

        // Inicialização dos clientes
        this.clientes = ClienteDAO.getClientes();
        this.cv = new ClienteView(clientes);
        this.cc = new ClienteController(cv, clientes);

        // Inicialização dos vendedores
        this.vendedores = VendedorDAO.getVendedores();
        this.vv = new VendedorView(vendedores);
        this.vc = new VendedorController(vv, vendedores);

        // Inicialização dos produtos
        this.produtos = ProdutoDAO.getProdutos();
        this.pv = new ProdutoView(produtos);
        this.pc = new ProdutoController(pv, produtos);
    }

    /**
     * Inicia a aplicação e gerencia o fluxo de operações com base nas escolhas do
     * usuário.
     */
    public void iniciar() {
        int op = -1;
        do {
            // Exibe o menu inicial e obtém a escolha do usuário
            op = AppView.menuInicial();

            // Realiza operações com base na escolha do usuário
            switch (op) {
                case 1: // Inserir cliente
                    boolean status = cc.criaCliente();
                    if (status == true) {
                        cv.mostraMsgCriacao();

                    }
                    break;
                case 2: // Listar clientes
                    if (!clientes.isEmpty()) {
                        cv.listarClientes();
                    } else {
                        AppView.mostraMsgListaVazia();
                    }
                    break;
                case 3: // Atualizar Cliente
                    cc.atualizarCliente();
                    break;
                case 4: // Excluir um Cliente
                    boolean deleted = cc.deletaCliente();
                    if (deleted) {
                        // Caso necessário essa é a lógica para tratamento após a exclusão
                    }
                    break;
                case 5: // inserir vendedor
                    status = vc.criaVendedor();
                    if (status == true) {
                        vv.mostraMsgCriacao();
                    }
                    break;
                case 6: // listar vendedores
                    if (!vendedores.isEmpty()) {
                        vv.listarVendedores();
                    } else {
                        AppView.mostraMsgListaVazia();
                    }
                    break;
                case 7: // atualizar vendedor
                    vc.atualizarVendedor();
                    break;
                case 8: // excluir um vendedor
                    deleted = vc.deletaVendedor();
                    if (deleted) {
                        // Caso necessário essa é a lógica para tratamento após a exclusão
                    }
                    break;
                case 9: // inserir produto
                    status = pc.criaProduto();
                    if (status == true) {
                        pv.mostraMsgCriacao();
                    }
                    break;
                case 10: // listar produtos
                    if (!produtos.isEmpty()) {
                        pv.listarProdutos();
                    } else {
                        AppView.mostraMsgListaVazia();
                    }
                    break;
                case 11: // atualizar produtos
                    pc.atualizarProduto();
                    break;
                case 12: // excluir um produto
                    deleted = pc.deletaProduto();
                    if (deleted) {
                        // Lógica para tratamento após a exclusão, se necessário
                    }
                    break;
                case 13: // sair do programa
                    AppView.mostraMsgFim();
                    op = -1;
                    break;
                default:
                    AppView.mostraMsgInvalida();
            }

        } while (op > 0);

    }
}
