/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import view.AppView;
import view.ClienteView;

/**
 *
 * @author Sâmeck
 */
public class AppController {

    private List<Cliente> clientes;
    private ClienteView cv;
    private ClienteController cc;
    private Cliente c;

    public AppController() {
        this.clientes = ClienteDAO.getClientes();
        this.cv = new ClienteView(clientes);
        this.cc = new ClienteController(cv, clientes);
    }

    public void iniciar() {
        int id = 0, op = -1, op1 = -1;
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
                case 3: // atualizar cliente
                    System.out.println("FUNÇÃO A SE FAZER");
                    break;
                case 4: //deletar cliente
                    int idParaDeletar = cv.defineCliente();
                    boolean deletado = cc.deletaCliente(idParaDeletar);
                    if(deletado){
                    cv.mostraMsgDelecao();
                    }else{
                        cv.mostraMsgClienteNaoEncontrado();                    }
                    break;
                case 5:
                    AppView.mostraMsgFim();
                    op = -1;
                    break;
                default:
                    AppView.mostraMsgInvalida();
            }

        } while (op > 0);

    }
}
