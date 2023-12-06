package view;

import java.util.List;
import java.util.Scanner;
import model.Vendedor;

/**
 *
 * @author Sâmeck
 */
public class VendedorView {

    private Scanner scan;
    private List<Vendedor> vendedores;

    /**
     * Costrutor da Classe Vendedor
     *
     * @param vendedores
     */
    public VendedorView(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
        this.scan = new Scanner(System.in);
    }

    /**
     * Listar os vendedores cadastrados
     */
    public void listarVendedores() {
        System.out.println("---------------------------");
        System.out.println("Vendedores:");
        System.out.println("---------------------------");

        for (Vendedor v : this.vendedores) {
            System.out.println(v.toString());
            System.out.println("---------------------------");
        }
    }

    /**
     * Entrar com os dados do vendedor
     *
     * @param i - representa o id
     * @return Vendedor
     */
    public Vendedor inputData(int i) {

        this.scan = new Scanner(System.in);
        System.out.println("Informe o nome do vendedor " + i + ": ");
        String nome = scan.nextLine();

        System.out.println("Informe o cnpj do vendedor " + i + ": ");
        String cnpj = scan.nextLine();

        System.out.println("Informe o endereco do vendedor " + i + ": ");
        String endereco = scan.nextLine();

        System.out.println("Informe o email do vendedor " + i + ": ");
        String email = scan.nextLine();

        System.out.println("Informe a senha do vendedor " + i + ": ");
        String senha = scan.nextLine();

        return new Vendedor(i, nome, cnpj, endereco, email, senha);
    }

    /**
     * Exibir msg de confirmação de criação do vendedor
     */
    public void mostraMsgCriacao() {
        System.out.println("-------------------------------");
        System.out.println("Vendedor criado com sucesso!");
        System.out.println("-------------------------------");
    }

    /**
     * Define o vendedor pelo id
     *
     * @return id
     */
    public int defineVendedor() {
        System.out.println("Informe o id do vendedor: ");
        return scan.nextInt();
    }

    /**
     * Exibir msg de confirmação de deleção do vendedor
     */
    public void mostraMsgDelecao() {

        System.out.println("-------------------------------");
        System.out.println("Vendedor deletado com sucesso!");
        System.out.println("-------------------------------");
    }

    public void mostraMsgVendedorNaoEncontrado() {
        System.out.println("-------------------------------");
        System.out.println("Vendedor não encontrado e/ou não existente");
        System.out.println("-------------------------------");
    }

    public Vendedor inputDataForUpdate() {
        System.out.println("Informe o ID do Vendedor que deseja atualizar:");
        int id = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        // Captura as informações visuais do cliente para atualização
        Vendedor vendedor = new Vendedor();
        vendedor.setId(id);

        System.out.println("Qual campo deseja atualizar?");
        System.out.println("1. Nome");
        System.out.println("2. Cnpj");
        System.out.println("3. Endereço");
        System.out.println("4. Email");
        System.out.println("5. Senha");
        int opcao = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scan.nextLine();
                vendedor.setNome(novoNome);
                break;
            case 2:
                System.out.println("Digite o novo cnpj:");
                String novoCnpj = scan.nextLine();
                vendedor.setCnpj(novoCnpj);
                break;
            case 3:
                System.out.println("Digite o novo endereço:");
                String novoEndereco = scan.nextLine();
                vendedor.setEndereco(novoEndereco);
                break;
            case 4:
                System.out.println("Digite o novo email:");
                String novoEmail = scan.nextLine();
                vendedor.setEmail(novoEmail);
                break;
            case 5:
                System.out.println("Digite a nova senha:");
                String novaSenha = scan.nextLine();
                vendedor.setSenha(novaSenha);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        return vendedor;
    }
}
