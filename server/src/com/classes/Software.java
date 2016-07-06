package com.classes;

import java.util.ArrayList;

public class Software extends Produto {
    public Double versao;

    public Software(String description, Double unityPrice, Fornecedor listaFornecedores) {
        super(description, unityPrice, listaFornecedores);
    }
}
