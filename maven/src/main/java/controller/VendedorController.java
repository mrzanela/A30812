package controller;

import dao.VendedorDAO;
import java.util.List;
import model.Vendedor;
import view.VendedorView;

/**
 * Controlador responsável por gerenciar operações relacionadas aos vendedores.
 * Interage com VendedorView e VendedorDAO
 * Utilizado pela classe AppController.
 * 
 * @author Sâmeck
 */
public class VendedorController {

    private Vendedor v;
    private VendedorView vv;
    private List<Vendedor> vendedores;

    /**
     * Construtor do VendedorController.
     *
     * @param vv         Instância da classe VendedorView.
     * @param vendedores Lista de vendedores.
     */

    public VendedorController(VendedorView vv, List<Vendedor> vendedores) {
        this.vv = vv;
        this.vendedores = vendedores;
    }

    /**
     * Cria um novo vendedor com base nos dados inseridos pelo usuário.
     *
     * @return true se o vendedor foi criado com sucesso, false caso contrário.
     */
    public boolean criaVendedor() {
        boolean status = false;
        int id = VendedorDAO.buscaCodigo() + 1;
        this.v = vv.inputData(id);
        if (this.v != null) {
            vendedores.add(v);
            VendedorDAO.saveVendedor(v);
            status = true;
        }
        return status;
    }

    /**
     * Deleta um vendedor com base no ID fornecido pelo usuário.
     *
     * @return true se o vendedor foi deletado com sucesso, false caso contrário.
     */
    public boolean deletaVendedor() {
        int id = vv.defineVendedor();
        boolean deleted = false;

        if (id > 0) {
            VendedorDAO dao = new VendedorDAO(); // Criar uma instância de VendedorDAO
            deleted = dao.deleteVendedor(id); // Chamar o método de instância deleteVendedor
            if (deleted) {
                // Tratamento após a exclusão
            }
        } else {
            System.out.println("ID inválido.");
        }

        return deleted;
    }

    /**
     * Atualiza um vendedor com base nos dados inseridos pelo usuário.
     *
     * @return true se o vendedor foi atualizado com sucesso, false caso contrário.
     */
    public boolean atualizarVendedor() {
        Vendedor vendedorParaAtualizar = vv.inputDataForUpdate();
        boolean atualizado = atualizarVendedorNoBanco(vendedorParaAtualizar);

        if (atualizado) {
            System.out.println("Vendedor atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o vendedor.");
        }
        return atualizado;
    }

    private boolean atualizarVendedorNoBanco(Vendedor vendedor) {
        VendedorDAO vendedorDAO = new VendedorDAO();
        return vendedorDAO.atualizarVendedor(vendedor);
    }
}