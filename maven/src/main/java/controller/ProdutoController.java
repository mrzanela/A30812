package controller;

import dao.ProdutoDAO;
import java.util.List;
import model.Produto;
import view.ProdutoView;

/**
 * Controlador responsável por gerenciar operações relacionadas aos produtos.
 * Interage com ProdutoView e ProdutoDAO
 * É utilizado pela classe AppController.
 * 
 * @author Sâmeck
 */
public class ProdutoController {
    private Produto p;
    private ProdutoView pv;
    private List<Produto> produtos;

    /**
     * Construtor do ProdutoController.
     *
     * @param pv       Instância da classe ProdutoView.
     * @param produtos Lista de produtos.
     */
    public ProdutoController(ProdutoView pv, List<Produto> produtos) {
        this.pv = pv;
        this.produtos = produtos;
    }

    /**
     * Cria um novo produto com base nos dados inseridos pelo usuário.
     *
     * @return true se o produto foi criado com sucesso, false caso contrário.
     */
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

    /**
     * Deleta um produto com base no ID fornecido pelo usuário.
     *
     * @return true se o produto foi deletado com sucesso, false caso contrário.
     */
    public boolean deletaProduto() {
        int id = pv.defineProduto();
        boolean deleted = false;

        if (id > 0) {
            ProdutoDAO dao = new ProdutoDAO(); // Criar uma instância de ProdutoDAO
            deleted = dao.deleteProduto(id); // Chamar o método de instância deleteVendedor
            if (deleted) {
                // Lógica para tratamento após a exclusão, se necessário
            }
        } else {
            System.out.println("ID inválido.");
        }

        return deleted;
    }

    /**
     * Atualiza um produto com base nos dados inseridos pelo usuário.
     *
     * @return true se o produto foi atualizado com sucesso, false caso contrário.
     */
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