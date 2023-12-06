package controller;

import dao.ProdutoDAO;
import java.util.List;
import model.Produto;
import view.ProdutoView;

/**
 *
 * @author Sâmeck
 */
public class ProdutoController {
    private Produto p;
    private ProdutoView pv;
    private List<Produto> produtos;

    public ProdutoController(ProdutoView pv, List<Produto> produtos) {
        this.pv = pv;
        this.produtos = produtos;
    }

    public boolean criaProduto() {
        boolean status = false;
        int id = ProdutoDAO.buscaCodigo() + 1;
        this.p = pv.inputData(id);
        if (this.p != null) {
            produtos.add(p);
            ProdutoDAO.saveProduto(p);
            status = true;
        }
        return status;
    }

    public boolean deletaProduto() {
        int id = pv.defineProduto();
        boolean deleted = false;

        if (id > 0) {
            ProdutoDAO dao = new ProdutoDAO(); // Criar uma instância de ProdutoDAO
            deleted = dao.deleteProduto(id); // Chamar o método de instância deleteVendedor
            if (deleted) {
            }
        } else {
            System.out.println("ID inválido.");
        }

        return deleted;
    }

    public boolean atualizarProduto() {
        Produto produtoParaAtualizar = pv.inputDataForUpdate();
        boolean atualizado = atualizarProdutoNoBanco(produtoParaAtualizar);

        if (atualizado) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o Produto.");
        }
        return atualizado;
    }

    private boolean atualizarProdutoNoBanco(Produto produto) {
       ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.atualizarProduto(produto);
    }
}
