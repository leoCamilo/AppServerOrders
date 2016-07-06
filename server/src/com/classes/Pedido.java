package com.classes;

import java.util.ArrayList;

public class Pedido {
    public int numero;
    public Cliente cliente;
    public ItemDeLinha itemDeLinha;

    public Pedido(int numero, Cliente cliente, ItemDeLinha itemDeLinha) {
        this.numero = numero;
        this.cliente = cliente;
        this.itemDeLinha = itemDeLinha;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", cliente=" + cliente +
                ", itemDeLinha=" + itemDeLinha +
                '}';
    }
}
