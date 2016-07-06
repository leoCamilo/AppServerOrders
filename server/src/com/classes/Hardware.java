package com.classes;

import java.util.ArrayList;

public class Hardware extends Produto {
    public String montagem;

    public Hardware(String description, Double unityPrice, Fornecedor listaFornecedores) {
        super(description, unityPrice, listaFornecedores);
    }
}
