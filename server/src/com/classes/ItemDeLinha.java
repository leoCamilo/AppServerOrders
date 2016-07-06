package com.classes;

public class ItemDeLinha {
    public int quantity;
    public Produto produto;

    public ItemDeLinha(int quantity, Produto produto) {
        this.quantity = quantity;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemDeLinha{" +
                "quantity=" + quantity +
                ", produto=" + produto +
                '}';
    }
}
