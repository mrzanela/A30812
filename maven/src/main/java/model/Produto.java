package model;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int qtd;

    public Produto() {
    }

    public Produto(int id, String nome, double preco, int qtd) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }

    public Produto(String nome, double preco, int qtd) {
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }
    
    @Override
    public String toString() {
        return "Produto{" +                 
                "nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", quantidade='" + qtd + '\'' +                
                '}';
    }

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
