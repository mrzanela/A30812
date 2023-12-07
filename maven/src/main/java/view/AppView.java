package view;

import java.util.Scanner;

/**
 * A classe AppView fornece métodos para interagir com o usuário através do
 * console, exibindo menus, mensagens e obtendo entrada do usuário. É utilizada
 * para a interação principal com o programa.
 *
 * @author Sâmeck
 */
public class AppView {

    public static int menuInicial() {

        // Scanner para obter entrada do usuário.
        Scanner scan = new Scanner(System.in);

        System.out.println("---------OPÇOES CLIENTE---------");
        System.out.println("Escolha uma opcao: ");
        System.out.println("[1] Inserir Cliente");
        System.out.println("[2] Listar Clientes");
        System.out.println("[3] Atualizar Clientes");
        System.out.println("[4] Excluir um Cliente");
        System.out.println("---------OPÇOES VENDEDOR---------");
        System.out.println("Escolha uma opcao: ");
        System.out.println("[5] Inserir Vendedor");
        System.out.println("[6] Listar Vendedores");
        System.out.println("[7] Atualizar Vendedores");
        System.out.println("[8] Excluir um Vendedor");
        System.out.println("---------OPÇOES PRODUTO---------");
        System.out.println("Escolha uma opcao: ");
        System.out.println("[9] Inserir Produto");
        System.out.println("[10] Listar Produtos");
        System.out.println("[11] Atualizar Produtos");
        System.out.println("[12] Excluir um Produto");
        System.out.println("[13] Sair");

        System.out.println("----------------------------");

        return scan.nextInt();
    }

    // Exibe mensagem indicando opção inválida.
    public static void mostraMsgInvalida() {
        System.out.println("-------------------------------");
        System.out.println("\"OPCAO INVALIDA!!!!\"");
        System.out.println("-------------------------------");
    }

    // Exibe mensagem indicando que nenhum registro foi encontrado.
    public static void mostraMsgNaoEncontrado() {
        System.out.println("-------------------------------");
        System.out.println("Registro não encontrado!");
        System.out.println("-------------------------------");
    }

    // Exibe mensagem indicando que a lista está vazia.
    public static void mostraMsgListaVazia() {
        System.out.println("-------------------------------");
        System.out.println("Nenhum registro encontrado!");
        System.out.println("-------------------------------");
    }

    // Exibe mensagem de despedida.
    public static void mostraMsgFim() {
        System.out.println("-------------------------------");
        System.out.println("ATÉ LOGO..");
        System.out.println("-------------------------------");
    }
}
