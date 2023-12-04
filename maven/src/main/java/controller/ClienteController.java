/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;
import view.ClienteView;

/**
 *
 * @author Sâmeck
 */
public class ClienteController {

    private Cliente c;
    private ClienteView cv;
    private List<Cliente> clientes;

    public ClienteController(ClienteView cv, List<Cliente> clientes) {
        this.cv = cv;
        this.clientes = clientes;
    }

    public boolean criaCliente() {
        boolean status = false;
        int id = ClienteDAO.buscaCodigo() + 1;
        this.c = cv.inputData(id);
        if (this.c != null) {
            clientes.add(c);
            ClienteDAO.saveCliente(c);
            status = true;
        }
        return status;
    }    
}
