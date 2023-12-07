package aplicacao;

import controller.AppController;

//Classe principal que contém o método main para iniciar a aplicação.
public class Main {

    /**
     *
     * Método principal que é chamado quando a aplicação é iniciada. Cria uma
     * instância do AppController e inicia a aplicação chamando o método
     * iniciar().
     */
    public static void main(String[] args) {// Cria uma instância do controlador da aplicação
        AppController ac = new AppController();

        // Inicia a aplicação chamando o método iniciar() do controlador
        ac.iniciar();
    }

}
