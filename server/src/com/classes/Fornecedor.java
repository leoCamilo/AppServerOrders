package com.classes;

import java.util.ArrayList;

public class Fornecedor {
    public int numero;
    public ArrayList <Produto> listaDeProdutos;
    public Endereco endereco;

    public Fornecedor(int numero, Endereco endereco) {
        this.numero = numero;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "numero=" + numero +
                ", listaDeProdutos=" + listaDeProdutos +
                ", endereco=" + endereco +
                '}';
    }
}
