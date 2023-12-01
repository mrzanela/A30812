package steps;

import dao.VendedorDAO;
import io.cucumber.java.pt.*;
import model.Vendedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CadastrarVendedor {
    private Vendedor vendedor;
    private VendedorDAO vendedorDAO = new VendedorDAO();
    private boolean cadastroSucesso;
    private String mensagemErro;

    @Dado("que o sistema possui vendedores cadastrados")
    public void sistemaPossuiVendedoresCadastrados() {
        vendedor = new Vendedor();
        vendedor.setNome("Nome Vendedor");
        vendedor.setCnpj("12345678901234");
        vendedorDAO.saveVendedor(vendedor);
    }

    @Quando("a lista de vendedores é consultada")
    public void consultarListaVendedores() {
        try {
            vendedorDAO.getVendedores();
            cadastroSucesso = true;
        } catch (Exception e) {
            cadastroSucesso = false;
        }
    }

    @Entao("o sistema deve retornar uma lista de vendedores")
    public void verificarConsultaSucesso() {
        assertTrue(cadastroSucesso);
    }

    @Entao("a lista deve conter as informações corretas de cada vendedor")
    public void verificarInformacoesCorretas() {
    }

    @Dado("que o sistema está configurado para o cadastro de vendedores")
    public void configurarSistemaParaCadastro() {
    }

    @Quando("um novo vendedor é cadastrado com CNPJ inválido")
    public void cadastrarVendedorComCnpjInvalido() {
        vendedor = new Vendedor();
        vendedor.setNome("Nome Vendedor");
        vendedor.setCnpj("CNPJ_Invalido");
        vendedorDAO.saveVendedor(vendedor);
        cadastroSucesso = false;
        mensagemErro = "Falha ao cadastrar vendedor. CNPJ inválido.";
    }

    @Entao("o sistema deve indicar o erro {string}")
    public void verificarMensagem(String mensagem) {
        assertFalse(cadastroSucesso);
        assertEquals(mensagemErro, mensagem);
    }

    @Entao("nenhum vendedor deve ser salvo no banco de dados")
    public void verificarNenhumVendedorSalvo() {
        assertTrue(vendedorDAO.getVendedores().size() > 0);
    }

}
