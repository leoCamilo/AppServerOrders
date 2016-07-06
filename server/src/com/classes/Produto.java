package com.classes;

public class Produto {
    private String description;
    private Double unityPrice;
    private Fornecedor listaFornecedores;

    public Produto(String description, Double unityPrice, Fornecedor listaFornecedores) {
        this.description = description;
        this.unityPrice = unityPrice;
        this.listaFornecedores = listaFornecedores;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "description='" + description + '\'' +
                ", unityPrice=" + unityPrice +
                ", listaFornecedores=" + listaFornecedores +
                '}';
    }
}
