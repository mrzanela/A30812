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
 *
 * @author Sâmeck
 */
public class AppController {

    private List<Cliente> clientes;
    private ClienteView cv;
    private ClienteController cc;
    private Cliente c;

    private List<Vendedor> vendedores;
    private VendedorView vv;
    private VendedorController vc;
    private Vendedor v;

    private List<Produto> produtos;
    private ProdutoView pv;
    private ProdutoController pc;
    private Produto p;

    public AppController() {
        this.clientes = ClienteDAO.getClientes();
        this.cv = new ClienteView(clientes);
        this.cc = new ClienteController(cv, clientes);

        this.vendedores = VendedorDAO.getVendedores();
        this.vv = new VendedorView(vendedores);
        this.vc = new VendedorController(vv, vendedores);

        this.produtos = ProdutoDAO.getProdutos();
        this.pv = new ProdutoView(produtos);
        this.pc = new ProdutoController(pv, produtos);
    }

    public void iniciar() {
        int op = -1;
        do {
            op = AppView.menuInicial();
            switch (op) {
                case 1: // inserir cliente
                    boolean status = cc.criaCliente();
                    if (status == true) {
                        cv.mostraMsgCriacao();

                    }
                    break;
                case 2: // listar clientes
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
                case 11: //atualizar produtos
                    pc.atualizarProduto();
                    break;
                case 12: // excluir um produto
                    deleted = pc.deletaProduto();
                    if (deleted) {
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
