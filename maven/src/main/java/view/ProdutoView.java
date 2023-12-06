package view;

import java.util.List;
import java.util.Scanner;
import model.Produto;

/**
 *
 * @author Sâmeck
 */
public class ProdutoView {
    private Scanner scan;
    private List<Produto> produtos;
    
    /**
     * Costrutor da Classe Produto
     *
     * @param produtos
     */
    public ProdutoView(List<Produto> produtos) {
        this.produtos = produtos;
        this.scan = new Scanner(System.in);
    }

    /**
     * Listar os produtos cadastrados
     */
    public void listarProdutos() {
        System.out.println("---------------------------");
        System.out.println("Produtos: ");
        System.out.println("---------------------------");

        for (Produto v : this.produtos) {
            System.out.println(v.toString());
            System.out.println("---------------------------");
        }
    }

    /**
     * Entrar com os dados do Produto
     *
     * @param i - representa o id
     * @return Produto
     */
    public Produto inputData(int i) {

        this.scan = new Scanner(System.in);
        System.out.println("Informe o nome do produto " + i + ": ");
        String nome = scan.nextLine();

        System.out.println("Informe o preço do produto " + i + ": ");
        double preco = scan.nextDouble();

        System.out.println("Informe a quantidade do produto " + i + ": ");
        int qtd = scan.nextInt();

        return new Produto(i, nome, preco, qtd);
    }

    /**
     * Exibir msg de confirmação de criação do Produto
     */
    public void mostraMsgCriacao() {
        System.out.println("-------------------------------");
        System.out.println("Produto criado com sucesso!");
        System.out.println("-------------------------------");
    }

    /**
     * Define o Produto pelo id
     *
     * @return id
     */
    public int defineProduto() {
        System.out.println("Informe o id do Produto: ");
        return scan.nextInt();
    }

    /**
     * Exibir msg de confirmação de deleção do Produto
     */
    public void mostraMsgDelecao() {

        System.out.println("-------------------------------");
        System.out.println("Produto deletado com sucesso!");
        System.out.println("-------------------------------");
    }

    public void mostraMsgProdutoNaoEncontrado() {
        System.out.println("-------------------------------");
        System.out.println("Produto não encontrado e/ou não existente");
        System.out.println("-------------------------------");
    }

    public Produto inputDataForUpdate() {
        System.out.println("Informe o ID do Produto que deseja atualizar:");
        int id = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        // Captura as informações visuais do cliente para atualização
        Produto produto = new Produto();
        produto.setId(id);

        System.out.println("Qual campo deseja atualizar?");
        System.out.println("1. Nome.");
        System.out.println("2. Preco.");
        System.out.println("3. Quantidade.");        
        int opcao = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scan.nextLine();
                produto.setNome(novoNome);
                scan.nextLine(); // Limpar o buffer do scanner
                break;
            case 2:
                System.out.println("Digite o novo Preco:");
                double novoPreco = scan.nextDouble();
                produto.setPreco(novoPreco);
                scan.nextLine(); // Limpar o buffer do scanner
                break;
            case 3:
                System.out.println("Digite a nova quantidade:");
                int novaQtd = scan.nextInt();
                produto.setQtd(novaQtd);
                break;          
            default:
                System.out.println("Opção inválida.");
                break;
        }

        return produto;
    }
}
