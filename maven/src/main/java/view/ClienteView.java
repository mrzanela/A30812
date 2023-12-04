/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Cliente;

/**
 *
 * @author Sâmeck
 */
public class ClienteView {

    private Scanner scan;
    private List<Cliente> clientes;

    /**
     * Costrutor da Classe Funcionario
     *
     * @param clientes
     */
    public ClienteView(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Listar os clientes cadastrados
     */
    public void listarClientes() {
        System.out.println("---------------------------");
        System.out.println("Clientes:");
        System.out.println("---------------------------");

        for (Cliente c : this.clientes) {
            System.out.println(c.toString());
            System.out.println("---------------------------");
        }
    }

    /**
     * Entrar com os dados do cliente
     *
     * @param i - representa o id
     * @return Funcionario
     */
    public Cliente inputData(int i) {

        this.scan = new Scanner(System.in);
        System.out.println("Informe o nome do cliente " + i + ": ");
        String nome = scan.nextLine();

        System.out.println("Informe o cpf do cliente " + i + ": ");
        String cpf = scan.nextLine();

        System.out.println("Informe o endereco do cliente " + i + ": ");
        String endereco = scan.nextLine();

        System.out.println("Informe o email do cliente " + i + ": ");
        String email = scan.nextLine();

        System.out.println("Informe a senha do cliente " + i + ": ");
        String senha = scan.nextLine();

        Date dataCadastro = new Date();

        return new Cliente(i, nome, cpf, endereco, email, senha);
    }
    /**
     * Exibir msg de confirmação de criação do cliente
     */
    public void mostraMsgCriacao() {
        System.out.println("-------------------------------");
        System.out.println("Funcionario criado com sucesso!");
        System.out.println("-------------------------------");
    }
    /**
     * Define o cliente pelo id
     * @return id
     */
    public int defineCliente(){
        System.out.println("Informe o id do cliente: ");
        return scan.nextInt();
    }
    
}
