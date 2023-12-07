package model;

public class Vendedor {

    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String email;
    private String senha;

    public Vendedor() {
    }

    // Construtor que inicializa todos os campos
    public Vendedor(int id, String nome, String cnpj, String endereco, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    // Construtor que não inclui id
    public Vendedor(String nome, String cnpj, String email, String senha) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Vendedor{"
                + "nome='" + nome + '\''
                + ", cnpj='" + cnpj + '\''
                + ", endereco='" + endereco + '\''
                + ", email='" + email + '\''
                + ", senha='" + senha + '\''
                + '}';
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
