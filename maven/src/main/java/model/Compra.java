package model;

import java.util.List;

public class Compra {

    private int id;
    private Cliente cliente;
    private List<ItemCompra> itens;
    private double valorTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void totalCompra() {
        double total = 0.0;
        for (ItemCompra item : itens) {
            total += item.getProduto().getPreco() * item.getQtd();
        }
        this.valorTotal = total;
    }

    public double getTotalCompra() {
        return valorTotal;
    }
}
